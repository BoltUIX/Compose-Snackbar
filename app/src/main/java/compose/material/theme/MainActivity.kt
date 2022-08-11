package compose.material.theme

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import compose.material.theme.ui.theme.Material3ComposeTheme
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Material3ComposeTheme {


                val context = LocalContext.current

                val snackState = remember { SnackbarHostState() }
                val scope = rememberCoroutineScope()

                Scaffold(
                    topBar = {
                    },
                    content = {

                        fun launchSnackbar(message: String, actionLabel : String?=null, duration: SnackbarDuration = SnackbarDuration.Short){
                            scope.launch {
                                snackState.showSnackbar(message = message,actionLabel=actionLabel, duration=duration)
                            }
                        }

                        Column(
                            modifier = Modifier
                                .padding(it)
                                .verticalScroll(rememberScrollState())
                        ) {

                            Spacer(modifier = Modifier.height(47.dp))



                            Text("Snackbar", Modifier.padding(bottom = 10.dp), style = MaterialTheme.typography.labelLarge)
                            Button(onClick = {
                                // *  Snackbar
                                launchSnackbar(message = "Hi i am snackbar message", actionLabel = "Hide", duration = SnackbarDuration.Long)
                            }) { Text("Snackbar",style = MaterialTheme.typography.labelLarge) }
                            ListDividerPadding()


                             Text("Toast", Modifier.padding(bottom = 10.dp), style = MaterialTheme.typography.labelLarge)
                             Button(onClick = {
                                 Toast.makeText(
                                     context,
                                     "Hi i am toast message",
                                     Toast.LENGTH_LONG
                                 ).show()
                             }) { Text("Toast",style = MaterialTheme.typography.labelLarge) }


                        }
                    }
                )

                Box(modifier = Modifier.fillMaxSize(), Alignment.BottomCenter){

                    /*  SnackbarHost(hostState = snackState)*/

                    // to change color
                    SnackbarHost(hostState = snackState) {
                        Snackbar(
                            snackbarData = it,
                            containerColor = MaterialTheme.colorScheme.primary,
                            contentColor = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                }

            }

        }
    }
}

