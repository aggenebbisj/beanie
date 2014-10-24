var manualBrewing = angular.module('breweryApp');
'use strict';

manualBrewing.factory("refreshService", function ($rootScope, restService) {
    return {
        refreshReadings: function (resourcePath, callback) {
            restService.get(resourcePath)
                    .success(function (response) {
                        callback(response);
                    }).error(function (response) {
            });
        }
    };
});
