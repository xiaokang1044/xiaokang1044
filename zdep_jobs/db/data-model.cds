namespace zdep;

using {managed} from '@sap/cds/common';

entity Week : managed {
  key ID          : String;
      Description : String;
}

entity Member : managed {
  key employeeId : String;
      name       : String;
      email      : String;
      photo      : String;
      isAdmin    : Boolean default false;
}

entity Image : managed {
  key ID          : String(3);
      Name        : String(30);
      ImageData   : LargeBinary; // 用于存储图片的二进制数据
      Description : String(255);
}


entity Project : managed {
  key ID : String;
      name : String;
}

entity Plan : managed {
  key weekId          : String;
      week            : Association to Week
                           on week.ID = $self.weekId;
  key userId          : String;
      member          : Association to Member
                           on member.employeeId = $self.userId;
  key projectId       : String;
      project         : Association to Project
                           on project.ID = $self.projectId;
      taskDescription : String;
      taskDetail      : String;
      planHours       : Decimal(3, 1);
      actualHours     : Decimal(3, 1);
      remark          : String;
}

