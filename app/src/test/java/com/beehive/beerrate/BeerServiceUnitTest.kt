package com.beehive.beerrate

import com.beehive.beerrate.service.BeerService
import com.beehive.beerrate.service.BeerServiceFactory
import org.junit.Test

class BeerServiceUnitTest {

    @Test
    fun fetchManyBeersWithBeerServiceAndThenFetchOneBeerByObjectIdSynchronously() {
        val createService = BeerServiceFactory.createService(BeerService::class.java)
        val manyResponse = createService.getBeers().execute()
        assert(manyResponse.body()!!.embedded.beers.count() > 0)
        println(manyResponse.body()!!.embedded.beers[0])
        val singleResponse =
            createService.getBeerByObjectId(manyResponse.body()!!.embedded.beers[0].objectId).execute()
        assert(singleResponse.body() != null)
        println(singleResponse.body()!!)
    }

    @Test
    fun fetchManyBeerStylesWithBeerServiceAndThenFetchOneBeerStyleByObjectIdSynchronously() {
        val createService = BeerServiceFactory.createService(BeerService::class.java)
        val manyResponse = createService.getBeerStyles().execute();
        assert(manyResponse.body()!!.embedded.beerStyles.count() > 0)
        println(manyResponse.body()!!.embedded.beerStyles[0])
        val singleResponse =
            createService.getBeerStyleByObjectId(manyResponse.body()!!.embedded.beerStyles[0].objectId).execute()
        assert(singleResponse.body() != null)
        println(singleResponse.body()!!)
    }

    @Test
    fun fetchManyBeerReviewsWithBeerServiceAndThenFetchOneBeerReviewByObjectIdSynchronously() {
        val createService = BeerServiceFactory.createService(BeerService::class.java)
        val manyResponse = createService.getBeerReviews().execute();
        assert(manyResponse.body()!!.embedded.beerReviews.count() > 0)
        println(manyResponse.body()!!.embedded.beerReviews[0])
        val singleResponse =
            createService.getBeerReviewByObjectId(manyResponse.body()!!.embedded.beerReviews[0].objectId).execute()
        assert(singleResponse.body() != null)
        println(singleResponse.body()!!)
    }

    @Test
    fun fetchManyBeerTypesWithBeerServiceAndThenFetchOneBeerTypeByObjectIdSynchronously() {
        val createService = BeerServiceFactory.createService(BeerService::class.java)
        val manyResponse = createService.getBeerTypes().execute();
        assert(manyResponse.body()!!.embedded.beerTypes.count() > 0)
        println(manyResponse.body()!!.embedded.beerTypes[0])
        val singleResponse =
            createService.getBeerTypeByObjectId(manyResponse.body()!!.embedded.beerTypes[0].objectId).execute()
        assert(singleResponse.body() != null)
        println(singleResponse.body()!!)
    }
}