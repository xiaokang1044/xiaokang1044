package customer.zdep_jobs.controllers;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.sap.cds.ql.Delete;
import com.sap.cds.ql.Select;
import com.sap.cds.ql.cqn.CqnDelete;
import com.sap.cds.ql.cqn.CqnSelect;
import com.sap.cds.services.draft.DraftService;

import com.sap.cds.services.persistence.PersistenceService;

import cds.gen.planservice.Plans;
import cds.gen.planservice.Plans_;
import customer.zdep_jobs.services.AlertService;
import customer.zdep_jobs.services.MailService;

@RestController
@RequestMapping("/rest")
public class DeleteController {

    @Autowired
    private PersistenceService db;

    @Autowired
    AlertService alertService;

    private final DraftService draftService;

    public DeleteController(DraftService draftService) {
        this.draftService = draftService;
        // this.persistenceService = persistenceService;
    }

    @PostMapping("/delete")
    public String getImage(@RequestBody String body) {
        CqnSelect selectQuery = Select.from(Plans_.class).where(e -> e.userId().eq(body));
        List<Plans> plans = draftService.run(selectQuery).listOf(Plans.class);
        System.out.println(plans);
        if (plans.size() > 0) {
            CqnDelete deleteQuery = Delete.from(Plans_.class)
                    .where(e -> e.userId().eq(body)
                            .and(e.IsActiveEntity().eq(false)));
            plans.get(0).getDraftAdministrativeDataDraftUUID();
            draftService.run(deleteQuery);
            return "delete";
        } else {
            return "get data fail";
        }

    }


    @PostMapping("/test")
    public String test() throws MessagingException  {
        System.out.println("test");
        alertService.checkAll();

        return "test";
    }
}
