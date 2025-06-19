package com.starter.app.presentation.screens.contributor

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import coil3.compose.AsyncImage
import com.starter.app.core.presentation.common.LoadingDialog
import com.starter.app.domain.model.Contributor
import com.starter.app.core.presentation.state.UiState
import com.starter.app.core.presentation.toUiText
import com.starter.app.core.util.ToastUtil
import org.koin.compose.koinInject

/**
 * Voyager navigation with screen to show list of contributors
 */
class ContributorsListScreen : Screen {

    @Composable
    override fun Content() {
        val viewModel: ContributorsViewModel = koinInject()
        val state by viewModel.state.collectAsState()

        LaunchedEffect(Unit) { viewModel.getContributors() }

        when (val uiState = state) {
            is UiState.Idle -> {}
            is UiState.Loading -> {
                LoadingDialog(true)
            }

            is UiState.Success -> {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    items(uiState.data) {
                        ContributorItem(it)
                    }
                }
            }

            is UiState.Error -> Text("Error: ${uiState.message.toUiText().asString()}")
        }
    }
}


@Composable
fun ContributorItem(contributor: Contributor) {
    Row(modifier = Modifier.padding(8.dp).clickable {
        ToastUtil.showToastMessage("${contributor.login} clicked")
    }) {
        AsyncImage(
            model = contributor.avatarUrl,
            contentDescription = contributor.login,
            modifier = Modifier.size(48.dp),
        )
        Spacer(Modifier.width(8.dp))
        Column {
            Text(contributor.login)
            Text("Contributions: ${contributor.contributions}")
        }
    }
}