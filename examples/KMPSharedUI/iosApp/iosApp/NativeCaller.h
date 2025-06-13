//
//  NativeCaller.h
//  iosApp
//
//  Created by Aleksander Grzegorzewski on 10/06/2025.
//

#ifndef NativeCaller_h
#define NativeCaller_h

#include <UIKit/UIKit.h>


@interface NativeCaller : NSObject

- (void)throwNative;

- (void)throwNativeAppHang;

@end

#endif /* NativeCaller_h */
