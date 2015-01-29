(function(angular) {
	var ApplicationRouter = function ($routeProvider, $locationProvider) {
		$routeProvider
			.when('/ui/services', {
				templateUrl: '/view/services.html',
				controller: 'ServicesController'
			})
			.when('/ui/services/:serviceId', {
				templateUrl: '/view/service.html',
				controller: 'ServicesController'
			})
			.when('/ui/instances/:instanceId', {
				templateUrl: '/view/service-instance.html',
				controller: 'ServiceInstanceController'
			})
			.when('/ui/profiles', {
				templateUrl: '/view/profiles.html',
				controller: 'ProfilesController'
			});
		
		// configure html5 to get links working on jsfiddle
		$locationProvider.html5Mode(true);
	};
	
	var MenuController = function($scope) {
		$scope.item = 0;
		
		$scope.selectItem = function(i) {
			$scope.item = i;
		};

		$scope.isSelected = function(i) {
			return $scope.item === i;
		};
	};
	
	var ServicesController = function($scope, Service) {
		$scope.service = "";
		
		Service.query(function(response) {
			$scope.services = response ? response : [];
		});
		
		$scope.selected = function() {
			return $scope.service !== "";
		};
		
		$scope.selectService = function(serviceId) {
			$scope.service = Service.get({ id: serviceId });
		};
		
		$scope.isSelected = function(serviceId) {
			return $scope.service.id === serviceId;
		};
		
		$scope.addService = function(name, description) {
			new Service({
				name: name,
				description: description
			}).$save(function(service) {
				$scope.services.push(service);
			});
			
			$scope.newService = "";
		};
		
		$scope.updateService = function(service) {
			service.$update();
		};
		
		$scope.deleteItem = function(service) {
			service.$remove(function() {
				$scope.services.splice($scope.services.indexOf(service), 1);
			});
		};
	};
	
	var ServiceInstanceController = function($scope, $routeParams, ServiceInstance) {
		$scope.serviceInstance = ServiceInstance.get({ id: $routeParams.instanceId });
		
		$scope.app = {
			artifactId: 'playmaker',
			groupId: 'org.cloudira.playmaker',
			version: '0.0.1-SNAPSHOT',
			status: 'UP',
			uptime: '00:00:57',
			processors: 8,
			classes: {
				current: 9976,
				loaded: 9977,
				unloaded: 1
			},
			threads: {
				current: 33,
				deamon: 31,
				peak: 33
			},
			memory: {
				total: '194.50M',
				used: '51.73M',
				percent: 27
			},
			heap: {
				initial: '128.00M',
				max: '1,810.00M',
				total: '194.50M',
				used: '51.73M',
				percent: 27
			},
			gc: {
				ps_scavenge_count: 14,
				ps_scavenge_time: 161,
				ps_marksweep_count: 2,
				ps_marksweep_time: 214,
			}
		};
		
	};
	
	var ProfilesController = function($scope, Profile) {
		Profile.query(function(response) {
			$scope.profiles = response ? response : [];
		});
		
		$scope.addProfile = function(name, description) {
			new Profile(
			{
				name: name,
				description: description
			}
			).$save(function(profile) {
				$scope.profiles.push(profile);
			});
			
			$scope.newService = "";
		};
		
		$scope.updateProfile = function(profile) {
			profile.$update();
		};
		
		$scope.deleteItem = function(profile) {
			profile.$remove(function() {
				$scope.profiles.splice($scope.profiles.indexOf(profile), 1);
			});
		};
	};
	
	ApplicationRouter.$inject = ['$routeProvider', '$locationProvider'];
	MenuController.$inject = ['$scope'];
	ServicesController.$inject = ['$scope', 'Service'];
	ServiceInstanceController.$inject = ['$scope', '$routeParams', 'ServiceInstance'];
	ProfilesController.$inject = ['$scope', 'Profile'];
	
	angular.module("playmaker.controllers")
		.config(ApplicationRouter)
		.controller("MenuController", MenuController)
		.controller("ServicesController", ServicesController)
		.controller("ServiceInstanceController", ServiceInstanceController)
		.controller("ProfilesController", ProfilesController);		
	
}(angular));