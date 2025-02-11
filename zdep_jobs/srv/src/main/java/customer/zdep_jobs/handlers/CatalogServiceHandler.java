package customer.zdep_jobs.handlers;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.sap.cds.ql.Insert;
import com.sap.cds.ql.Select;
import com.sap.cds.ql.cqn.CqnSelect;
import com.sap.cds.services.cds.CqnService;
import com.sap.cds.services.draft.DraftService;
import com.sap.cds.services.handler.EventHandler;
import com.sap.cds.services.handler.annotations.Before;
import com.sap.cds.services.handler.annotations.On;
import com.sap.cds.services.handler.annotations.ServiceName;
import com.sap.cds.services.persistence.PersistenceService;

import cds.gen.planservice.CopyPlansContext;
import cds.gen.planservice.PlanService_;
import cds.gen.planservice.Plans;
import cds.gen.planservice.Plans_;
import cds.gen.zdep.Member;
import cds.gen.zdep.Member_;
import cds.gen.zdep.Project;
import cds.gen.zdep.Project_;
import cds.gen.zdep.Week;
import cds.gen.zdep.Week_;

@Component
@Service
@ServiceName(PlanService_.CDS_NAME)
public class CatalogServiceHandler implements EventHandler {

    @Autowired
    private PersistenceService db;

    // @Autowired
    // private DraftService draftService;

    // private final PersistenceService persistenceService;
	private final DraftService draftService;

	public CatalogServiceHandler(DraftService draftService) {
		this.draftService = draftService;
		// this.persistenceService = persistenceService;
	}

    @Before(event = { CqnService.EVENT_CREATE, CqnService.EVENT_UPDATE })
    public void validatePlans(final Plans plans) throws Exception {
        System.out.println("testxk");
        System.out.println(plans.getProjectId());
        System.out.println(plans.getWeekId());
        if (checkProject(plans.getProjectId())) {
            System.out.println(plans.getProjectId());
            throw new Exception("Please select correct project ID!");
        }

        if (checkWeek(plans.getWeekId())) {
            System.out.println(plans.getWeekId());
            throw new Exception("Please select correct project ID!");
        }

    };

    private boolean checkProject(String projectCode) {
        CqnSelect sel = Select.from(Project_.class).where(e -> e.ID().eq(projectCode));
        // Execute the query and return the results
        List<Project> result = db.run(sel).listOf(Project.class);
        if (result.size() > 0) {
            return false;
        }
        return true;
    }

    private boolean checkWeek(String weekId) {
        CqnSelect sel = Select.from(Week_.class).where(e -> e.ID().eq(weekId));
        // Execute the query and return the results
        List<Week> result = db.run(sel).listOf(Week.class);
        if (result.size() > 0) {
            return false;
        }
        return true;
    }

    @On(event = "copyPlans" )
    public void copyPlans(CopyPlansContext context) throws Exception {

        System.out.println("testxk");
        // UserInfo userInfo =  context.getUserInfo();
        // String email = userInfo.getId();
        String email = "test";
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            // 假设邮箱地址存储在用户名中
            email = userDetails.getUsername();
            String[] parts = email.split("/");
            if (parts.length > 2) {
                email =  parts[2];
            }
        }
        final String femail = email;
        System.out.println("email:" + email);
        List<Member> members= db.run( Select.from(Member_.class).where(e -> e.email().eq(femail))).listOf(Member.class);
        if (members.size() == 0) {
            throw new Exception("User information not found Please contact system Administrator jeremy_m_li@wistron.com");
        }
        String name = members.get(0).getEmployeeId();
        // //获取当前周数
        String weekId = getWeek();
        String lastWeekId = getLastWeek();
        //减一周 然后去select

        // 然后copy 数据   存成 draft
        // List<Plans> plans= db.run( Select.from(Plans_.class).where(e -> e.userId().eq(name).and(e.week().equals(weekId))) ).listOf(Plans.class);
        List<Plans> plans = db.run(Select.from(Plans_.class)
                .where(e -> e.userId().eq(name).and(e.weekId().eq(lastWeekId))))
                .listOf(Plans.class);
                plans.forEach(e -> e.setWeekId(weekId));
        // Plans plan = Plans.create();
        // plan.setProjectId("1");
        // plan.setWeekId("1");
        // plan.setUserId("Xiaokang Bai");
        // draftService.run(Insert.into(Plans_.class).entry(plan));
        draftService.newDraft(Insert.into(Plans_.class).entries(plans));
        context.setCompleted();
    };

    public String getWeek(){
        LocalDate currentDate = LocalDate.now();
        LocalDate startOfWeek = currentDate.with(DayOfWeek.MONDAY);
        LocalDate endOfWeek = currentDate.with(DayOfWeek.SUNDAY);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String startOfWeekStr = startOfWeek.format(formatter);
        String endOfWeekStr = endOfWeek.format(formatter);

        // 拼接结果字符串
        String result = startOfWeekStr + "-" + endOfWeekStr;
        return result;
    }

    public String getLastWeek() {
        LocalDate currentDate = LocalDate.now();
        LocalDate startOfLastWeek = currentDate.minusWeeks(1).with(DayOfWeek.MONDAY);
        LocalDate endOfLastWeek = currentDate.minusWeeks(1).with(DayOfWeek.SUNDAY);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String startOfLastWeekStr = startOfLastWeek.format(formatter);
        String endOfLastWeekStr = endOfLastWeek.format(formatter);

        // 拼接结果字符串
        String result = startOfLastWeekStr + "-" + endOfLastWeekStr;
        return result;
    }

}