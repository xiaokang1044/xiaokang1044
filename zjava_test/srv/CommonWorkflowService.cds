service CommonProxyPeriodDataService {
    entity proxy_period as projection on PeriodData.proxy_period;
}

service CommonAdminDataService {
    entity user         as projection on AdminData.user;
    entity employee_org as projection on AdminData.employee_org;
}

service CommonBaseDataService {
    entity account_definition          as projection on BaseData.account_definition;
    entity exchange_rate               as projection on BaseData.exchange_rate;
    entity document_type               as projection on BaseData.document_type;
    entity document_name               as projection on BaseData.document_name;
    entity proxy_document_statusperiod as projection on BaseData.document_status;
    entity fin_list                    as projection on BaseData.fin_list;
    entity fin_default_pic             as projection on BaseData.fin_default_pic;
    entity fin_preview_pic             as projection on BaseData.fin_preview_pic;
    entity level_test                  as projection on BaseData.level;
    entity approve_amount              as projection on BaseData.approve_amount;
    entity pre_approver                as projection on BaseData.pre_approver;
    entity company_code_org            as projection on BaseData.company_code_org;
    entity site                        as projection on BaseData.site;
    entity authority_pic               as projection on BaseData.authority_pic;
    entity sales_order_reason          as projection on BaseData.sales_order_reason;
    entity account_fin                 as projection on BaseData.account_fin;
    entity customer_master             as projection on BaseData.customer_master;
    entity customer_account            as projection on BaseData.customer_account;
    entity production_type             as projection on BaseData.production_type;
    entity payment_term                as projection on BaseData.payment_term;
    entity release_reason              as projection on BaseData.release_reason;
    entity other_group                 as projection on BaseData.other_group;
}
