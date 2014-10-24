angular.module('breweryApp').factory('websocketService', function ($rootScope) {
    'use strict';

    var websocket = new WebSocket($rootScope.wsUri + 'monitor');
    
    

    return {
        registerEvent: function (callback) {
            websocket.onmessage = function(data) {
                callback(data);
            };
        }
    };
});