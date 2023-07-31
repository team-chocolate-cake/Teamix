package com.chocolate.teamix

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.chocolate.remote.server_and_organizations.service.OrganizationService
import com.chocolate.teamix.ui.theme.TeamixTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var message: Message

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                GlobalScope.launch {
                    delay(3000)
                }
                true
            }
        }
        setContent {
            TeamixTheme {

            }
        }
        message.getMessage()
    }

}

/*
deleteMessage
getMessages
editMessage
addEmojiReaction
deleteEmojiReaction
markAllMessagesAsRead
 */
class Message @Inject constructor(private val organizationService: OrganizationService) {
    fun getMessage() {
        Log.d("Message", "getMessage() called") // Log message for entering the function
        runBlocking {
            val response = organizationService.getServerSettings()
            if (response.isSuccessful) {
                Log.d(
                    "success",
                    "success"
                ) // Log message for successful response
            } else {
                Log.e("Message", "getMessage() failed") // Log message for failed response
            }
        }
    }
}
//class Message @Inject constructor(private val messageService: MessageService) {
//    fun getMessage() {
//        Log.d("Message", "getMessage() called") // Log message for entering the function
//        runBlocking {
//            val response: Response<MatchNarrowRemoteDto> = messageService
//                .checkIfMessagesMatchNarrow(
//                    msg_ids = listOf(379616403, 378962951),
//                    narrow = NarrowQuery(listOf(NarrowFilter("has", "link")))
//                )
//            if (response.isSuccessful) {
//                Log.d(
//                    "Message",
//                    "getMessage() successful ${response.body()}"
//                ) // Log message for successful response
//            } else {
//                Log.e("Message", "getMessage() failed") // Log message for failed response
//            }
//        }
//    }
//}