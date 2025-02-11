sap.ui.require(
    [
        'sap/fe/test/JourneyRunner',
        'zdep/plans1/test/integration/FirstJourney',
		'zdep/plans1/test/integration/pages/PlansList',
		'zdep/plans1/test/integration/pages/PlansObjectPage'
    ],
    function(JourneyRunner, opaJourney, PlansList, PlansObjectPage) {
        'use strict';
        var JourneyRunner = new JourneyRunner({
            // start index.html in web folder
            launchUrl: sap.ui.require.toUrl('zdep/plans1') + '/index.html'
        });

       
        JourneyRunner.run(
            {
                pages: { 
					onThePlansList: PlansList,
					onThePlansObjectPage: PlansObjectPage
                }
            },
            opaJourney.run
        );
    }
);