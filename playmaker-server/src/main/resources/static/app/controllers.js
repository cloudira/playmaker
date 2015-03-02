(function(angular) {
	var ApplicationRouter = function ($routeProvider, $locationProvider) {
		$routeProvider
			.when('/ui/services', {
				templateUrl: '/view/services.html',
				controller: 'ServicesController'
			})
			.when('/ui/services/:serviceId', {
				templateUrl: '/view/service.html',
				controller: 'ServiceController'
			})
			.when('/ui/services/:serviceId/config/:profileName/:itemId?', {
				templateUrl: '/view/service-config.html',
				controller: 'ServiceConfigController'
			})
			.when('/ui/services/:serviceId/settings', {
				templateUrl: '/view/service-edit.html',
				controller: 'ServiceEditController'
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
		
		$scope.createService = function() {
			$scope.newService = new Service({
				name: null,
				description: null
			});
		};
		
		$scope.addService = function(newService) {
			$scope.services.push(newService);
			//newService.$save(function(service) {
			//	$scope.services.push(service);
			//});
			
			$scope.newService = null;
		};
		
		$scope.cancelService = function() {
			$scope.newService = null;
		}
		
		$scope.updateService = function(service) {
			service.$update();
		};
		
		$scope.deleteItem = function(service) {
			service.$remove(function() {
				$scope.services.splice($scope.services.indexOf(service), 1);
			});
		};
	};
	
	var ServiceController = function($scope, $routeParams, Service) {
		$scope.service = Service.get({
			id: $routeParams.serviceId
		});
		
		$scope.tab = 0;
		
		$scope.isTabSelected = function(checkTab) {
			return this.tab === checkTab;
		};
		
	};
	
	var ServiceConfigController = function($scope, $routeParams, Service, ServiceProperty, Profile) {
		$scope.service = Service.get({ id: $routeParams.serviceId });
		
		if ($routeParams.itemId == null) {
			$scope.config = Service.config({ id: $routeParams.serviceId, profileName: $routeParams.profileName })
		} else {
			$scope.config = Service.config({ id: $routeParams.serviceId, profileName: $routeParams.profileName, itemId: $routeParams.itemId })
		}
		
		Profile.query(function(response) {
			$scope.profiles = response ? response : [];
		});
		
		$scope.tab = 1;
		
		$scope.isTabSelected = function(checkTab) {
			return this.tab === checkTab;
		};
		
		$scope.createProfile = function() {
			$scope.newProfile = new Profile({
				name: null,
				description: null
			});
		};
		
		$scope.addProfile = function(newProfile) {
			newProfile.$save(function(profile) {
				$scope.profiles.push(profile);
			});
			
			$scope.newProfile = null;
		};
		
		$scope.cancelProfile = function() {
			$scope.newProfile = null;
		}
		
		$scope.createProperty = function() {
			$scope.newProperty = new ServiceProperty({
				type: 'property',
				name: null,
				value: null
			});
		};
		
		$scope.addProperty = function(newProperty) {
			var data = {
				serviceId: $scope.service.id,
				profileName: $scope.config.profile,
				id: $scope.config.item.id
			};
			
			newProperty.$save(data, function(property) {
				var newConfig = Service.config({ id: data.serviceId, profileName: data.profileName, itemId: data.id });
				$scope.config = newConfig;
			});
			$scope.newProperty = null;
		};
		
		$scope.cancelProperty = function() {
			$scope.newProperty = null;
		};
		
		$scope.updateProperty = function() {
			var data = {
				serviceId: $scope.service.id,
				profileName: $scope.config.profile,
				id: $scope.config.item.id
			};
			
			property = new ServiceProperty({
				type: 'property',
				name: $scope.config.item.name,
				value: $scope.config.item.value
			});
			
			property.$update(data, function(resp) {
				//var newConfig = Service.config({ id: data.serviceId, profileName: data.profileName, itemId: data.id });
				//$scope.config = newConfig;
			});
		};
		
	};
	
	var ServiceEditController = function($scope, $routeParams, Service) {
		$scope.service = Service.get({ id: $routeParams.serviceId });
		
		$scope.tab = 2;
		
		$scope.isTabSelected = function(checkTab) {
			return this.tab === checkTab;
		};
		
		$scope.updateService = function(service) {
			service.$update();
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
	
	var InstanceMetricsController = function($scope, $routeParams, ServiceInstance) {
		$scope.metrics = ServiceInstance.metrics({ id: $routeParams.instanceId });
	};
	
	var InstanceEnvironmentController = function($scope, $routeParams, ServiceInstance) {
		$scope.env = ServiceInstance.env({ id: $routeParams.instanceId });
	};
	
	var InstanceBeansController = function($scope, $routeParams, ServiceInstance) {
		$scope.contexts = ServiceInstance.beans({ id: $routeParams.instanceId });
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
	ServiceController.$inject = ['$scope', '$routeParams', 'Service'];
	ServiceConfigController.$inject = ['$scope', '$routeParams', 'Service', 'ServiceProperty', 'Profile'];
	ServiceEditController.$inject = ['$scope', '$routeParams', 'Service'];
	ServiceInstanceController.$inject = ['$scope', '$routeParams', 'ServiceInstance'];
	InstanceDetailsController.$inject = ['$scope', '$routeParams', 'ServiceInstance'];
	InstanceMetricsController.$inject = ['$scope', '$routeParams', 'ServiceInstance'];
	InstanceEnvironmentController.$inject = ['$scope', '$routeParams', 'ServiceInstance'];
	InstanceBeansController.$inject = ['$scope', '$routeParams', 'ServiceInstance'];
	ProfilesController.$inject = ['$scope', 'Profile'];
	
	angular.module("playmaker.controllers")
		.config(ApplicationRouter)
		.controller("MenuController", MenuController)
		.controller("ServicesController", ServicesController)
		.controller("ServiceController", ServiceController)
		.controller("ServiceConfigController", ServiceConfigController)
		.controller("ServiceEditController", ServiceEditController)
		.controller("ServiceInstanceController", ServiceInstanceController)
		.controller("InstanceDetailsController", InstanceDetailsController)
		.controller("InstanceMetricsController", InstanceMetricsController)
		.controller("InstanceEnvironmentController", InstanceEnvironmentController)
		.controller("InstanceBeansController", InstanceBeansController)
		.controller("ProfilesController", ProfilesController);		
	
}(angular));