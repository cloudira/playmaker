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
		
		$scope.tab = 0;
		
		$scope.tabs = [{
			title: 'Application', 
			page: '/view/instance/tab-details.html'
		}, {
			title: 'Metrics', 
			page: '/view/instance/tab-metrics.html'
		}, {
			title: 'Environment',
			page: '/view/instance/tab-env.html'
		}, {
			title: 'Configuration',
			page: '/view/instance/tab-config.html'
		}, {
			title: 'Threads',
			page: '/view/instance/tab-threads.html'
		}, {
			title: 'Classpath',
			page: '/view/instance/tab-classpath.html'
		}, {
			title: 'Logging',
			page: '/view/instance/tab-logging.html'
		}, {
			title: 'Trace',
			page: '/view/instance/tab-trace.html'
		}, {
			title: 'Beans',
			page: '/view/instance/tab-beans.html'
		},{
			title: 'Mapping',
			page: '/view/instance/tab-mapping.html'
		}]
		
		$scope.selectTab = function (setTab) {
			this.tab = setTab;
		};
		
		$scope.isTabSelected = function(checkTab) {
			return this.tab === checkTab;
		};
		
		$scope.includeTab = function() {
			return $scope.tabs[$scope.tab].page;
		};
		
	};
	
	var InstanceDetailsController = function($scope, $routeParams, ServiceInstance) {
		$scope.app = ServiceInstance.details({ id: $routeParams.instanceId });
	};
	
	var InstanceEnvironmentController = function($scope, $routeParams, ServiceInstance) {
		$scope.env = ServiceInstance.env({ id: $routeParams.instanceId });
	};
	
	var InstanceBeansController = function($scope, $routeParams, ServiceInstance) {
		$scope.ctx = ServiceInstance.beans({ id: $routeParams.instanceId });
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
	InstanceDetailsController.$inject = ['$scope', '$routeParams', 'ServiceInstance'];
	InstanceEnvironmentController.$inject = ['$scope', '$routeParams', 'ServiceInstance'];
	InstanceBeansController.$inject = ['$scope', '$routeParams', 'ServiceInstance'];
	ProfilesController.$inject = ['$scope', 'Profile'];
	
	angular.module("playmaker.controllers")
		.config(ApplicationRouter)
		.controller("MenuController", MenuController)
		.controller("ServicesController", ServicesController)
		.controller("ServiceInstanceController", ServiceInstanceController)
		.controller("InstanceDetailsController", InstanceDetailsController)
		.controller("InstanceEnvironmentController", InstanceEnvironmentController)
		.controller("InstanceBeansController", InstanceBeansController)
		.controller("ProfilesController", ProfilesController);		
	
}(angular));