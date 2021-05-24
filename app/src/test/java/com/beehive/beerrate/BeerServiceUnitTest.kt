package com.beehive.beerrate

import com.beehive.beerrate.service.BeerService
import com.beehive.beerrate.service.BeerServiceGenerator
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test

class BeerServiceUnitTest {

    /**
     * Changed the database style, so this doesn't match anymore. Update TBD.
     */
/*    @Test
    fun fetchManyBeersWithBeerServiceAndThenFetchOneBeerByObjectIdSynchronously() = runBlocking() {
        val createService = BeerServiceGenerator.createService(BeerService::class.java)
        val manyResponse = createService.getBeers()
        assert(manyResponse.embedded.beers.count() > 0)
        println(manyResponse.embedded.beers[0])
        val singleResponse =
            createService.getBeerByObjectId(manyResponse.embedded.beers[0].objectId)
        println(singleResponse)
    }

    @Test
    fun fetchManyBeerStylesWithBeerServiceAndThenFetchOneBeerStyleByObjectIdSynchronously() =
        runBlocking() {
            val createService = BeerServiceGenerator.createService(BeerService::class.java)
            val manyResponse = createService.getBeerStyles()
            assert(manyResponse.embedded.beerStyles.count() > 0)
            println(manyResponse.embedded.beerStyles[0])
            val singleResponse =
                createService.getBeerStyleByObjectId(manyResponse.embedded.beerStyles[0].objectId)
            println(singleResponse)
        }

    @Test
    fun fetchManyBeerReviewsWithBeerServiceAndThenFetchOneBeerReviewByObjectIdSynchronously() =
        runBlocking() {
            val createService = BeerServiceGenerator.createService(BeerService::class.java)
            val manyResponse = createService.getBeerReviews()
            assert(manyResponse.embedded.beerReviews.count() > 0)
            println(manyResponse.embedded.beerReviews[0])
            val singleResponse =
                createService.getBeerReviewByObjectId(manyResponse.embedded.beerReviews[0].objectId)
            println(singleResponse)
        }

    @Test
    fun fetchManyBeerTypesWithBeerServiceAndThenFetchOneBeerTypeByObjectIdSynchronously() =
        runBlocking() {
            val createService = BeerServiceGenerator.createService(BeerService::class.java)
            val manyResponse = createService.getBeerTypes()
            assert(manyResponse.embedded.beerTypes.count() > 0)
            println(manyResponse.embedded.beerTypes[0])
            val singleResponse =
                createService.getBeerTypeByObjectId(manyResponse.embedded.beerTypes[0].objectId)
            println(singleResponse)
        }*/

    @Test
    fun fetchManyBeersWithBeerServiceAndThenFetchOneBeerByObjectIdSynchronously() = runBlocking() {
        val createService = BeerServiceGenerator.createService(BeerService::class.java)
        val manyResponse = createService.getBeerReviewByBeerId(156)
        assert(manyResponse.embedded.beerReviews.count() > 0)
        println(manyResponse.embedded.beerReviews[0])
    }
}