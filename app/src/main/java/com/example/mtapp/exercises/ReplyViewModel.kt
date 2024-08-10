package com.example.mtapp.exercises

import androidx.lifecycle.ViewModel
import com.example.mtapp.data.local.exercises.Email
import com.example.mtapp.data.local.exercises.MailboxType
import com.example.mtapp.data.local.exercises.LocalEmailsDataProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class ReplyViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(ReplyUiState())
    val uiState: StateFlow<ReplyUiState> = _uiState

    init {
        initializeUIState()
    }

    private fun initializeUIState() {
        var mailboxes: Map<MailboxType, List<Email>> =
            LocalEmailsDataProvider.allEmails.groupBy { it.mailbox }
        _uiState.value = ReplyUiState(
            mailboxes = mailboxes,
            currentSelectedEmail = mailboxes[MailboxType.Inbox]?.get(0)
                ?: LocalEmailsDataProvider.defaultEmail
        )
    }

    fun updateDetailsScreenStates(email: Email) {
        _uiState.update {
            it.copy(
                currentSelectedEmail = email,
                isShowingHomepage = false
            )
        }
    }

    fun resetHomeScreenStates() {
        _uiState.update {
            it.copy(
                currentSelectedEmail = it.mailboxes[it.currentMailbox]?.get(0)
                    ?: LocalEmailsDataProvider.defaultEmail,
                isShowingHomepage = true
            )
        }
    }

    fun updateCurrentMailbox(mailboxType: MailboxType) {
        _uiState.update {
            it.copy(
                currentMailbox = mailboxType
            )
        }
    }
}