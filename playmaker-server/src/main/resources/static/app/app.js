(function(angular) {
	angular.module("playmaker.controllers", []);
	angular.module("playmaker.services", []);
	angular.module("playmaker", ["ui.bootstrap", "ngResource", "ngRoute", "playmaker.controllers", "playmaker.services"]);
}(angular));
