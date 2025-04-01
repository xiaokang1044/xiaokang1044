using zvcf as workflow from '../db/common-workflow';
using zvcf as approvalData from '../db/common-approval-data';
using zvcf as numberRange from '../db/common-number-range-data';
using zvcf as proxyPeriod from '../db/common-proxy-period-data';
using zvcf as baseData from '../db/common-base-data';
using zvcf as helpDesk from '../db/common-help-desk-data';

service CommonWorkflowService {
    entity CommonCnoInstanceMaps as projection on workflow.com_cno_instance_map;
}

service CommonApprovalDataService {
    entity CommonApprovalLists as projection on approvalData.com_approval_list;
}

service CommonNumberRangeService {
    entity CommonNumberRanges as projection on numberRange.com_number_range;
}

service CommonProxyPeriodDataService {
    entity CommonProxyPeriods as projection on proxyPeriod.com_proxy_period;
}

service CommonBaseDataService {
    entity CommonLanguageKeys as projection on baseData.com_language;
}

service CommonHelpDeskDataService {
    entity CommonHelpDesks as projection on helpDesk.com_help_desk;
}