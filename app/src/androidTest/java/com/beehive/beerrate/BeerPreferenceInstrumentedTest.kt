package com.beehive.beerrate

import android.content.Context
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.beehive.beerrate.model.BeerStyle
import com.beehive.beerrate.model.BeerType
import com.beehive.beerrate.service.BeerPreference
import junit.framework.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BeerPreferenceInstrumentedTest {

    lateinit var instrumentationContext: Context
    lateinit var beerPreference: BeerPreference

    @Before
    fun setupContext() {
        // Context of the app under test.
        instrumentationContext = InstrumentationRegistry.getInstrumentation().targetContext
        beerPreference = BeerPreference(instrumentationContext)
    }

    @Test
    fun beerTypesTest() {
        val actualBeerTypes =
            mutableListOf(
                BeerType(1, true, "Anglo-American Ales"),
                BeerType(2, true, "Lagers"),
                BeerType(3, true, "Belgian-Style Ales")
            )
        beerPreference.setBeerTypes(actualBeerTypes)
        val expectedBeerTypes = beerPreference.getBeerTypes()
        assertEquals(expectedBeerTypes, actualBeerTypes)
        actualBeerTypes.addAll(expectedBeerTypes)
        beerPreference.setBeerTypes(actualBeerTypes)
        val moreExpectedBeerTypes = beerPreference.getBeerTypes()
        assertNotSame(moreExpectedBeerTypes, actualBeerTypes)
        beerPreference.clearBeerTypes()
        val clearBeerType = beerPreference.getBeerTypes()
        assert(clearBeerType.isEmpty())
    }

    @Test
    fun beerStylesTest() {
        val actualBeerStyles =
            mutableListOf(
                BeerStyle(
                    1,
                    2,
                    "Altbier / Sticke Alt",
                    "The Altbier is a well-balanced, well-attenuated, bitter yet malty, clean, and smooth, amber to copper colored top-fermented lagered German-style beer. The bitterness is balanced by the malt richness, but the malt intensity and character can range from moderate to high. More bitter and malty than international amber lagers. The Sticke Alt and Doppelsticke Alt are a maltier and hoppier versions of traditional Altbier.",
                    1,
                    true
                ),
                BeerStyle(
                    2,
                    11,
                    "Barley Wine / Wheat Wine / Rye Wine",
                    "An English Barley Wine is a showcase of malty richness and complex, intense flavors. Chewy and rich in body, with warming alcohol and a pleasant fruity or hoppy interest. When aged, it can take on port-like flavors.Although often a hoppy beer, the English Barleywine places less emphasis on hop character than the American Barley Wine. In the American versions, the hop character should be evident throughout, but does not have to be unbalanced. The alcohol strength and hop bitterness often combine to leave a very long finish. English versions can be darker, maltier, fruitier, and feature richer specialty malt flavors than American Barley Wines and has some overlap British Old Ale on the lower end, but generally does not have the vinous qualities of age. American versions differs from a Double IPA in that the hops are not extreme, the malt is more forward, and the body is fuller, often richer and typically has more residual sweetness than a Double IPA. The Wheat Wine is more than simply a wheat-based barley wine, many versions have an emphasis on the bready, wheaty flavors with interesting complexity from malt, hops, fruity yeast character and alcohol complexity and it as less emphasis on the hops than American Barleywine.",
                    2,
                    true
                ),
                BeerStyle(
                    1,
                    20,
                    "Bitter - Ordinary / Best",
                    "An English Barley Wine is a showcase of malty richness and complex, intense flavors. Chewy and rich in body, with warming alcohol and a pleasant fruity or hoppy interest. When aged, it can take on port-like flavors.Although often a hoppy beer, the English Barleywine places less emphasis on hop character than the American Barley Wine. In the American versions, the hop character should be evident throughout, but does not have to be unbalanced. The alcohol strength and hop bitterness often combine to leave a very long finish. English versions can be darker, maltier, fruitier, and feature richer specialty malt flavors than American Barley Wines and has some overlap British Old Ale on the lower end, but generally does not have the vinous qualities of age. American versions differs from a Double IPA in that the hops are not extreme, the malt is more forward, and the body is fuller, often richer and typically has more residual sweetness than a Double IPA. The Wheat Wine is more than simply a wheat-based barley wine, many versions have an emphasis on the bready, wheaty flavors with interesting complexity from malt, hops, fruity yeast character and alcohol complexity and it as less emphasis on the hops than American Barleywine.",
                    3,
                    true
                )
            )
        beerPreference.setBeerStyles(actualBeerStyles)
        val expectedBeerStyles = beerPreference.getBeerStyles()
        assertEquals(expectedBeerStyles, actualBeerStyles)
        actualBeerStyles.addAll(expectedBeerStyles)
        beerPreference.setBeerStyles(actualBeerStyles)
        val moreExpectedBeerStyles = beerPreference.getBeerStyles()
        assertNotSame(moreExpectedBeerStyles, actualBeerStyles)
        beerPreference.clearBeerStyles()
        val clearBeerType = beerPreference.getBeerStyles()
        assert(clearBeerType.isEmpty())
    }
}