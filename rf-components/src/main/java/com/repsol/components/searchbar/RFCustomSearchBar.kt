package com.repsol.components.searchbar

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.repsol.components.icon.RFIcon
import com.repsol.components.style.RFColor
import com.repsol.components.style.RFTextStyle
import com.repsol.components.text.RFText

@Composable
fun RFCustomSearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    icon: Int,
    onSearchClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    RFCustomSearchBarImpl(
        query = query,
        onQueryChange = onQueryChange,
        icon = icon,
        onSearchClick = onSearchClick,
        modifier = modifier
    )

}

@Composable
private fun RFCustomSearchBarImpl(
    query: String,
    onQueryChange: (String) -> Unit,
    onSearchClick: () -> Unit,
    icon: Int,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .heightIn(48.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(RFColor.UxComponentColorWhite.color)
            .border(1.dp, RFColor.UxComponentColorDarkGray.color, shape = RoundedCornerShape(8.dp))
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            BasicTextField(
                value = query,
                onValueChange = onQueryChange,
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 12.dp, top = 12.dp, bottom = 12.dp)
                    .align(Alignment.CenterVertically),
                singleLine = true,
                textStyle = TextStyle(
                    fontSize = 16.sp,
                    color = RFColor.UxComponentColorCharcoal.color
                ),
                decorationBox = { innerTextField ->
                    if (query.isEmpty()) {
                        RFText(
                            text = "Buscar",
                            textStyle = RFTextStyle.Roboto(
                                fontSize = 16.sp,
                                color = RFColor.UxComponentColorDarkGray
                            )
                        )
                    }

                    innerTextField()
                }
            )

            Box(
                modifier = Modifier
                    .heightIn(48.dp)
                    .widthIn(60.dp)
                    .background(RFColor.UxComponentColorDiamond.color)
                    .clickable(onClick = onSearchClick),
                contentAlignment = Alignment.Center
            ) {

                RFIcon(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(icon),
                )
            }
        }
    }

}
