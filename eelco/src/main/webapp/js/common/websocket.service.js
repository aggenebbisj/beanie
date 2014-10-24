angular.module('breweryApp').factory('websocketService', function ($rootScope) {
    'use strict';

    var websocket = new WebSocket($rootScope.wsUri + 'monitor');
    
    return {
        onopen: function (callback) {
            websocket.onopen = function(data) {
                callback(data);
            };
        },
        onmessage: function (callback) {
            websocket.onmessage = function(data) {
                callback(data);
            };
        }
    };
});