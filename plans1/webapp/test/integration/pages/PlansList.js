sap.ui.define(['sap/fe/test/ListReport'], function(ListReport) {
    'use strict';

    var CustomPageDefinitions = {
        actions: {},
        assertions: {}
    };

    return new ListReport(
        {
            appId: 'zdep.plans1',
            componentId: 'PlansList',
            contextPath: '/Plans'
        },
        CustomPageDefinitions
    );
});