// Copyright 2019 Tulip Solutions B.V.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//      http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package main

import (
	"context"
	"fmt"
	"github.com/tulipsolutions/tecl/tulipsolutions/api/common/orders"
	"github.com/tulipsolutions/tecl/tulipsolutions/api/pub/ohlc"
	"google.golang.org/grpc"
	"os"
	"time"
)

func publicOhlcServiceGetOhlcData(conn *grpc.ClientConn, parentContext context.Context) {
	client := ohlc.NewPublicOhlcServiceClient(conn)

	// Create a request for the OhlcBins for specified market and intervals.
	request := ohlc.GetOhlcRequest{
		Market: orders.Market_BTC_EUR,
		Intervals: []ohlc.Interval{ohlc.Interval_ONE_SECOND, ohlc.Interval_ONE_MINUTE, ohlc.Interval_FIVE_MINUTES},
	}

	// Create a new context with a 1s deadline and make the request synchronously
	ctx, cancel := context.WithTimeout(parentContext, time.Second)
	defer cancel()
	ohlcBin, err := client.GetOhlcData(ctx, &request)
	if err != nil {
		_, _ = fmt.Fprintf(os.Stderr, "PublicOhlcService.GetOhlcData error: %v \n", err)
		return
	}
	fmt.Println(ohlcBin)
}
