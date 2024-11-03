import * as React from "react";

import { Card, CardContent } from "@/components/ui/card";
import {
  Carousel,
  CarouselContent,
  CarouselItem,
  CarouselNext,
  CarouselPrevious,
} from "@/components/ui/carousel";

const Listing = () => {
  return (
    <div className="mt-8 p-4 ">
      <div className="flex flex-col gap-2">
        <h3 className="font-bold text-xl">Popular Events in Delhi</h3>
        <Carousel
          opts={{
            align: "start",
          }}
          className="w-full max-w-7xl "
        >
          <CarouselContent>
            {Array.from({ length: 5 }).map((_, index) => (
              <CarouselItem key={index} className="md:basis-1/3 lg:basis-1/4">
                <div className="p-1">
                  <Card>
                    <CardContent className="flex aspect-square items-center justify-center p-6">
                      <span className="text-3xl font-semibold">
                        {index + 1}
                      </span>
                    </CardContent>
                  </Card>
                </div>
              </CarouselItem>
            ))}
          </CarouselContent>
          <CarouselPrevious className="mx-2" />
          <CarouselNext className="mx-2" />
        </Carousel>
      </div>
    </div>
  );
};

export default Listing;
