#import "CDVConfigBuddy.h"

@implementation CDVConfigBuddy

- (void)pluginInitialize {
    printf("Starting Config Buddy plugin");

    [[NSNotificationCenter defaultCenter]
     addObserver:self
     selector:@selector(preferredContentSizeChanged:)
     name:UIContentSizeCategoryDidChangeNotification
     object:nil];
}

- (void)preferredContentSizeChanged:(NSNotification *)notification {
    NSString* textSize = [[notification userInfo] objectForKey:UIContentSizeCategoryNewValueKey];
    printf("textSize: %s", [textSize UTF8String]);
}

- (void)getSystemTextSize:(CDVInvokedUrlCommand*)command {
    NSString* textSize = [[UIApplication sharedApplication] preferredContentSizeCategory];

	CDVPluginResult* result = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:textSize];
	[self.commandDelegate sendPluginResult:result callbackId:command.callbackId];
}

- (void)getTextZoom:(CDVInvokedUrlCommand*)command {
	// Android only
}

- (void)setTextZoom:(CDVInvokedUrlCommand*)command {
    // Android only
}

- (void) isPowerSaveEnabled:(CDVInvokedUrlCommand*)command {
    bool isLowPowerModeEnabled = [[NSProcessInfo processInfo] isLowPowerModeEnabled];

    CDVPluginResult* result = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsBool:isLowPowerModeEnabled];
    [self.commandDelegate sendPluginResult:result callbackId:command.callbackId];
}

- (void)openAppSettings:(CDVInvokedUrlCommand*)command {
    [[UIApplication sharedApplication] openURL:[NSURL URLWithString:UIApplicationOpenSettingsURLString]];
}

@end
