using {zdep as my} from '../db/data-model';


service PlanService {
    entity Users    as projection on my.Member;
    annotate Users with @odata.draft.enabled @readonly;
    entity Weeks    as projection on my.Week;
    annotate Weeks with @odata.draft.enabled @readonly;
    entity Projects as projection on my.Project;
    annotate Projects with @odata.draft.enabled @readonly;
    entity Plans    as projection on my.Plan;
    annotate Plans with @odata.draft.enabled;
    action copyPlans();
}

annotate PlanService.Plans with {
    weekId          @title: 'Week';
    userId          @title: 'Employee ID';
    projectId       @title: 'Project Code';
    taskDescription @title: 'Task Description';
    taskDetail      @title: 'Task Detail';
    planHours       @title: 'Planned Hours';
    actualHours     @title: 'Actual Hours';
    remark          @title: 'Remark';
};

annotate PlanService.Plans with @Aggregation.ApplySupported: {
    Transformations       : [
        'aggregate',
        'topcount',
        'bottomcount',
        'identity',
        'concat',
        'groupby',
        'filter',
        'search'
    ],
    Rollup                : true,
    PropertyRestrictions  : true,
    GroupableProperties   : [
        'weekId',
        'userId',
        'projectId'
    ],
    AggregatableProperties: [
        {Property: planHours},
        {Property: actualHours},
    ],
};
