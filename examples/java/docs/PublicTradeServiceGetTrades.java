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

package docs;

import io.grpc.ManagedChannel;
import io.grpc.stub.StreamObserver;
import nl.tulipsolutions.api.common.Market;
import nl.tulipsolutions.api.pub.GetPublicTradesRequest;
import nl.tulipsolutions.api.pub.PublicTradeServiceGrpc;
import nl.tulipsolutions.api.pub.PublicTradeServiceGrpc.PublicTradeServiceStub;
import nl.tulipsolutions.api.pub.PublicTrades;

import java.util.concurrent.TimeUnit;

public class PublicTradeServiceGetTrades {
    public static void run(ManagedChannel channel) {
        PublicTradeServiceStub stub = PublicTradeServiceGrpc.newStub(channel);

        // Create a request for the most recent trades in the BTC_EUR market
        GetPublicTradesRequest request = GetPublicTradesRequest.newBuilder()
            .setMarket(Market.BTC_EUR)
            .build();

        // Make the request asynchronously with a one second deadline
        stub.withDeadlineAfter(1, TimeUnit.SECONDS)
            .getTrades(request, new StreamObserver<PublicTrades>() {
                public void onNext(PublicTrades value) {
                    System.out.println(value);
                }

                public void onError(Throwable t) {
                    System.err.println("PublicTradeService.GetTrades error: " + t.getMessage());
                }

                public void onCompleted() {
                    System.out.println("PublicTradeService.GetTrades completed");
                }
            });
    }
}
