//
//  NativeCaller.m
//  iosApp
//
//  Created by Aleksander Grzegorzewski on 10/06/2025.
//

#import <Foundation/Foundation.h>
#include <stdlib.h>
#import "NativeCaller.h"

@implementation NativeCaller

- (void)throwNative {
    abort();
}

- (void)throwNativeAppHang {
    [NSThread sleepForTimeInterval:3];
    _exit(1);
}

@end
