/*
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *
*/

var exec = require('cordova/exec');

/**
 * Provides Android Integration w/ WalkMe.
 */
module.exports = {
    sendGoal : function() {
        var nativeArgs = [];

        if(arguments.length === 0 || typeof arguments[0] === "undefined" || arguments[0].length === 0) {
            return false;
        } else {
            nativeArgs.unshift(arguments[0]);
        }

        if (arguments.length > 1 && typeof arguments[1] === "object") {
            nativeArgs.push(arguments[1]);
        }

        if (nativeArgs.length > 0) {
            exec(null, null, 'WalkMe', 'sendGoal', nativeArgs);
        } else {
            return false;
        }
    }
};
