/*
 Licensed to the Apache Software Foundation (ASF) under one
 or more contributor license agreements.  See the NOTICE file
 distributed with this work for additional information
 regarding copyright ownership.  The ASF licenses this file
 to you under the Apache License, Version 2.0 (the
 "License"); you may not use this file except in compliance
 with the License.  You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing,
 software distributed under the License is distributed on an
 "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 KIND, either express or implied.  See the License for the
 specific language governing permissions and limitations
 under the License.
 */

#import "WalkMe.h"

@implementation WalkMe

- (void)pluginInitialize
{
    NSString* appKey = [[[NSBundle mainBundle] infoDictionary] objectForKey:@"WalkMeAppKey"];
    NSString* appSecret = [[[NSBundle mainBundle] infoDictionary] objectForKey:@"WalkMeAppSecret"];

    [ABBI start:[NSString stringWithString:appKey] withSecretKey:[NSString stringWithString:appSecret] andApplicationType:ABBI_APP_HYBRID];
}

- (void) sendGoal:(CDVInvokedUrlCommand *)command {
    NSArray* args = command.arguments;
    NSUInteger argc = [args count];
    NSString* goalName = [NSString stringWithString:[command argumentAtIndex:0 withDefault:[NSNull null]]];

    if( argc > 1 ) {
        NSDictionary* properties = [command argumentAtIndex:1 withDefault:[NSNull null]];
        [ABBI sendGoal:goalName withProperites:properties];
    } else if ( argc == 1 ){
        [ABBI sendGoal:goalName withProperites:nil];
    }
}

@end
