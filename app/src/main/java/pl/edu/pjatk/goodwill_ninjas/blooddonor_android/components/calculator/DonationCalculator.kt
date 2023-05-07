package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.components.calculator

import android.util.Log
import android.widget.DatePicker
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.mapSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.components.DatePicker
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.database.donation.DonationDao
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.viewmodels.donation.DonationViewModel


@Composable
fun DonationCalculator(donationViewModel: DonationViewModel = viewModel()) {
    var lastDonationDate by mutableStateOf("")
    fun updateLastDonationDate(dateInput: String){
        lastDonationDate = dateInput
    }
//    var dateChangeListener = DateListener()
//    var date = remember {
//        mutableStateOf("")
//    }
    var blood by rememberSaveable(stateSaver = BloodSaver) {
        mutableStateOf(HorizontalPagerContent("Krew", "2023-05-25"))
    }
    Column(modifier = Modifier.background(Color.White)) {
        Row {
            DatePicker(
                updateDate = { donationViewModel.updateLastDonationDate(it) },
                date = donationViewModel.lastDonationDate,
            )
            Log.d("date", "date + $donationViewModel.lastDonationDate")
        }
        Row {
            IntroScreen()
        }
    }
    Row(){
        Column() {
            Text(text ="z viewModela" + donationViewModel.lastDonationDate)
        }
    }
}

data class HorizontalPagerContent(var name: String, val date: String)

val BloodSaver = run {
    val nameKey = "name"
    val dateKey = "date"
    mapSaver(save = { mapOf(nameKey to it.name, dateKey to it.date) },
        restore = { HorizontalPagerContent(it[nameKey] as String, it[dateKey] as String) })
}
val bs = BloodSaver

val getList: List<HorizontalPagerContent> =
    listOf(
        HorizontalPagerContent(
            "Krew Pełna",
            "2022-05-26"
        ),
        HorizontalPagerContent(
            "Osocze",
            "2022-07-26"
        ),
        HorizontalPagerContent(
            "Krwinki",
            "2022-08-26"
        ),
        HorizontalPagerContent(
            "Płytki krwi",
            "A022-09-26"
        ),

        )


@OptIn(ExperimentalPagerApi::class)
@Composable
fun IntroScreen() {

    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()
    val isNextVisible = remember {
        derivedStateOf {
            pagerState.currentPage != getList.size - 1
        }
    }
    val isPrevVisible = remember {
        derivedStateOf {
            pagerState.currentPage != 0
        }
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        Box(
            modifier = Modifier
                .fillMaxHeight(0.75f)
                .fillMaxWidth()
        ) {
            HorizontalPager(count = getList.size, state = pagerState) { currentPage ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(horizontal = 20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = getList[currentPage].name,
                        color = Color.Black
                    )
                    Text(
                        text = getList[currentPage].date,
                        color = Color.Black
                    )
                }
            }
        }
        HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            pageCount = getList.size,
            indicatorWidth = 15.dp,
            indicatorHeight = 15.dp,
            activeColor = Color.Blue,
            inactiveColor = Color.Yellow
        )
    }
}

@Composable
@Preview
fun Seeit() {
    DonationCalculator()
}