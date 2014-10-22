var manualBrewing = angular.module('breweryApp');
'use strict';

manualBrewing.factory("refreshService", function ($rootScope, restService) {
    return {
        refreshReadings: function (resourcePath) {
            restService.get(resourcePath)
                    .success(function (response) {
                        console.log(response);
                        $rootScope.brewer.readings = {
                            'capacity': response.ingredients.reduce(function (a, b) {
                                return (a.volume && a.volume.value || a) + b.volume.value;
                            }, 0),
                            'temperature': response.temperature.value,
                            'locked': 0
                        };
                    }).error(function (response) {
                console.log(response);
            });
        }
    };
});
