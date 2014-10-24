angular.module('breweryApp').factory("refreshService", function ($rootScope, restService) {
    'use strict';
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
