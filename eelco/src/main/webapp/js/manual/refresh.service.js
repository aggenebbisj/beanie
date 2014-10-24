var manualBrewing = angular.module('breweryApp');
'use strict';

manualBrewing.factory("refreshService", function ($rootScope, restService) {
    return {
        refreshReadings: function (resourcePath, callback) {
            restService.get(resourcePath)
                    .success(function (response) {
                        console.log('respons refresh');
                        console.log(response);
                        console.log('temperature ' + response.temperature.value);
                        $rootScope.brewer.readings = {
                            'capacity': response.ingredients.reduce(function (a, b) {
                                return (a.volume && a.volume.value || a) + b.volume.value;
                            }, 0),
                            'temperature': response.temperature.value,
                        };
                        callback();
                    }).error(function (response) {
            });
        }
    };
});
