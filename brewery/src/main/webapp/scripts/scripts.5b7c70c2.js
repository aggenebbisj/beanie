"use strict";angular.module("breweryApp",["ngAnimate","ngCookies","ngResource","ngRoute","ngSanitize","ngTouch","ngJustGage"]).config(["$routeProvider",function(a){a.when("/",{templateUrl:"views/main.html",controller:"MainCtrl"}).when("/live",{templateUrl:"views/live.html",controller:"LiveCtrl"}).when("/recipe",{templateUrl:"views/recipe.html",controller:"RecipeCtrl"}).otherwise({redirectTo:"/"})}]),angular.module("breweryApp").controller("HeaderController",["$scope","$location",function(a,b){a.isActive=function(a){return a===b.path()}}]),angular.module("breweryApp").controller("MainCtrl",["$scope","$rootScope",function(a,b){b.serverUrl="http://localhost:8080",a.updateUrl=function(a){b.serverUrl=a}}]),angular.module("breweryApp").controller("LiveCtrl",["$scope","restService","$rootScope",function(a,b,c){function d(b,c){var d=new Date,e={time:d.toLocaleString(),message:b,success:c};a.messages.push(e)}function e(a,c){b.postWithData(a,c).success(function(a){d(a,!0),console.log("success: "+a)}).error(function(a){d(a,!1),console.log("failed:  "+a)})}function f(a,c){b.putWithData(a,c).success(function(a){angular.isObject(a)&&(d(a,!0),console.log("success: "+a))}).error(function(a){d(a,!1),console.log("failed:  "+a)})}a.error={value:!1,text:""},"undefined"==typeof c.serverUrl&&(c.serverUrl="http://localhost:8080",c.resourcePath="/brewery/resources/kettle/"),a.messages=[],a.operations={water:{operation:"add",name:"water",unit:"liter",value:0,activate:function(a){if(console.log(a),0!==a.value){var d=c.serverUrl+c.resourcePath+"ingredients";e(d,b.createIngredient(a))}}},barley:{operation:"add",name:"barley",unit:"liter",value:0,activate:function(a){if(console.log(a),0!==a.value){var d=c.serverUrl+c.resourcePath+"ingredients";e(d,b.createIngredient(a))}}},hops:{operation:"add",name:"hops",unit:"liter",value:0,activate:function(a){if(console.log(a),0!==a.value){var d=c.serverUrl+c.resourcePath+"ingredients";e(d,b.createIngredient(a))}}},yeast:{operation:"add",name:"yeast",unit:"liter",value:0,activate:function(a){if(console.log(a),0!==a.value){var d=c.serverUrl+c.resourcePath+"ingredients";e(d,b.createIngredient(a))}}},temperature:{operation:"change",unit:"celsius",name:"temperature",value:0,activate:function(a){if(console.log(a),0!==a.value){var d=c.serverUrl+c.resourcePath+"temperature";f(d,b.createTemperature(a))}}},waiting:{operation:"",unit:"minutes",name:"wait",value:0,activate:function(a){if(console.log(a),0!==a.value){var d=c.serverUrl+c.resourcePath;e(d,b.createWaitingMessage(a))}}}}}]),angular.module("breweryApp").controller("RecipeCtrl",["$scope","restService","$rootScope",function(a,b,c){function d(b){a.recipeSteps.push(b)}function e(a,c){b.postWithData(a,c).success(function(a){f(a),console.log("success: "+a)}).error(function(a){g(a),console.log("failed:  "+a)})}function f(b){a.error.value=!0,a.error.text=b}function g(b){a.error.value=!0,a.error.text=b}a.recipeSteps=[],a.recipeName="","undefined"==typeof c.serverUrl&&(c.serverUrl="http://localhost:8080",c.resourcePath="/brewery/resources/kettle/",c.recipePath="/brewery/resources/brewer/recipe"),a.operations={yeast:{operation:"add",name:"yeast",unit:"liter",value:0,activate:function(a){var b={type:"addIngredient",ingredient:a.name,volume:{value:a.value,unit:a.unit}};d(b)}},water:{operation:"add",name:"water",unit:"liter",value:0,activate:function(a){var b={ingredient:{name:a.name,volume:{value:a.value,unit:a.unit}}};d(b)}},temperature:{operation:"change",unit:"celsius",name:"temperature",value:0,activate:function(a){var b={type:"changeTemperature",temperature:{value:a.value,scale:a.unit}};d(b)}},waiting:{operation:"",unit:"minutes",name:"wait",value:0,activate:function(a){var b={type:"stableTemperature",duration:"PT"+a.value+"M"};d(b)}}},a.submitRecipe=function(){var b={name:a.recipeName,steps:a.recipeSteps};e(c.serverUrl+c.recipePath,b)}}]);var test;angular.module("breweryApp").controller("MonitorCtrl",["$scope","$rootScope",function(a,b){function c(){var b=new WebSocket(e);b.onopen=function(){a.readings={capacity:0,temperature:0}},b.onmessage=function(b){var c=JSON.parse(b.data);console.log("remko"),console.log(c),test=c,console.log("pim2"),console.log(c.ingredient.volume),a.readings={capacity:a.readings.capacity+(c.ingredient.volume.value||0),temperature:c.kettle&&c.kettle.value||a.readings.temperature}},b.onerror=function(){}}a.reading={capacity:0,temperature:0},a.error={value:!1,text:""},"undefined"==typeof b.serverUrl&&(b.serverUrl="http://localhost:8080");var d=window.location.pathname.split("/")[1],e="ws://"+document.location.host+"/"+d+"/brewer";c()}]),angular.module("breweryApp").factory("restService",["$http",function(a){return{get:function(b){return a({method:"GET",url:b})},post:function(b){return a({method:"POST",url:b})},postWithData:function(b,c){return a({method:"POST",url:b,headers:{"Content-Type":"application/json"},data:c})},putWithData:function(b,c){return a({method:"PUT",url:b,headers:{"Content-Type":"application/json"},data:c})},createIngredient:function(a){return{name:a.name,volume:{value:a.value,unit:a.unit}}},createTemperature:function(a){return{value:a.value,unit:a.unit}},createWaitingMessage:function(a){return{value:a.value,unit:a.unit}}}}]);