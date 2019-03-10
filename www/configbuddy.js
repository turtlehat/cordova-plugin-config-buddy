module.exports = {
	getSystemTextSize: function(successCallback) {
		cordova.exec(successCallback, null, 'ConfigBuddy', 'getSystemTextSize');
	},
	getTextZoom: function(successCallback) {
		cordova.exec(successCallback, null, 'ConfigBuddy', 'getTextZoom');
	},
	setTextZoom: function(zoom) {
		cordova.exec(null, null, 'ConfigBuddy', 'setTextZoom', [ zoom ]);
	},
	isPowerSaveEnabled: function(successCallback) {
		cordova.exec(successCallback, null, 'ConfigBuddy', 'isPowerSaveEnabled');
	},
	openAppSettings: function() {
		cordova.exec(null, null, 'ConfigBuddy', 'openAppSettings');
	}
};
