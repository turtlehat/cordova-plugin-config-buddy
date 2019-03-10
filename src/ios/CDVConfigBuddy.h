#import <Cordova/CDV.h>

@interface CDVConfigBuddy : CDVPlugin

- (void) getSystemTextSize:(CDVInvokedUrlCommand*)command;
- (void) getTextZoom:(CDVInvokedUrlCommand*)command;
- (void) setTextZoom:(CDVInvokedUrlCommand*)command;
- (void) isPowerSaveEnabled:(CDVInvokedUrlCommand*)command;
- (void) openAppSettings:(CDVInvokedUrlCommand*)command;

@end