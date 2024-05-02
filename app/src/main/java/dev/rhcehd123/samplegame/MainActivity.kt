package dev.rhcehd123.samplegame

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.android.gms.games.AuthenticationResult
import com.google.android.gms.games.PlayGames
import com.google.android.gms.tasks.Task
import dagger.hilt.android.AndroidEntryPoint
import dev.rhcehd123.designsystem.theme.SampleGameTheme
import dev.rhcehd123.samplegame.ui.SampleGameMain
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val userIdState = MutableStateFlow("")
            val userId by userIdState.collectAsStateWithLifecycle()
            val gamesSignInClient = PlayGames.getGamesSignInClient(this)

            gamesSignInClient.isAuthenticated()
                .addOnCompleteListener { isAuthenticatedTask: Task<AuthenticationResult> ->
                    val isAuthenticated = isAuthenticatedTask.isSuccessful &&
                            isAuthenticatedTask.result.isAuthenticated
                    if (isAuthenticated) {
                        // Continue with Play Games Services
                        PlayGames.getPlayersClient(this).getCurrentPlayer(false).addOnCompleteListener listener@{
                            if(it.isSuccessful) {
                                val player = it.result.get()
                                if(player == null) {
                                    Toast.makeText(this, "Google Play Games Error", Toast.LENGTH_SHORT).show()
                                    finish()
                                    return@listener
                                }
                                userIdState.update { player.playerId }
                                Toast.makeText(this, player.playerId, Toast.LENGTH_SHORT).show()
                            } else {

                            }
                        }
                    } else {
                        // Disable your integration with Play Games Services or show a
                        // login button to ask  players to sign-in. Clicking it should
                        // call GamesSignInClient.signIn().
                        gamesSignInClient.signIn().addOnCompleteListener { task ->
                            if(task.isSuccessful) {
                                val result = task.result
                            } else {

                            }
                        }
                            .addOnFailureListener {
                                it.printStackTrace()
                            }
                    }
                }

            SampleGameTheme {
                SampleGameMain(userId = userId)
            }
        }
    }
}