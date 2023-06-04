package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.components.carousel

import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.components.badgeCard.BadgeCard
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.database.userFeat.UserFeat

@Composable
fun Carousel(
    items: List<UserFeat>,
    modifier: Modifier = Modifier
) {
    val listState = rememberLazyListState(Int.MAX_VALUE/2)
    LazyRow( state = listState, modifier = modifier) {
        items(
            count = Int.MAX_VALUE,
            itemContent = {
                items.forEach {item ->
                    BadgeCard(item)
                }
            }
        )
    }
    
}