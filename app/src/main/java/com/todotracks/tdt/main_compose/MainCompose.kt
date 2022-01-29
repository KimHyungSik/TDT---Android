package com.todotracks.tdt.main_compose

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.fragment.app.Fragment
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.todotracks.tdt.main_compose.common.Screens
import com.todotracks.tdt.databinding.FragmentMainTopicListBinding
import com.todotracks.tdt.main_compose.main_topic_added.mainTopicAddedScreen
import com.todotracks.tdt.main_compose.main_topic_list.mainTopicListScreen
import com.todotracks.tdt.main_compose.sub_topic_added.subTopicAddedScreen
import com.todotracks.tdt.ui.theme.TDTTheme

class MainTopicListScreen : Fragment() {

    private var _binding: FragmentMainTopicListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainTopicListBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.composeView.setContent {
            val navController = rememberNavController()
            TDTTheme {
                NavigationGraph(navController)
            }
        }
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screens.MainTopicListScreen.url) {
        composable(Screens.MainTopicListScreen.url) {
            mainTopicListScreen(navController)
        }
        composable(Screens.MainTopicAddedScreen.url) {
            mainTopicAddedScreen(navController)
        }
        composable(
            Screens.SubTopicAddedScreen.url + "/{main_topic_id}/{main_topic_title}",
            arguments = listOf(
                navArgument(
                    name = "main_topic_id"
                ){
                    type = NavType.IntType
                },
                navArgument(
                    name = "main_topic_title"
                ){
                    type = NavType.StringType
                }
            )
        ) {
            val mainTopicId = it.arguments?.getInt("main_topic_id")
            val mainTopicTitle = it.arguments?.getString("main_topic_title")
            subTopicAddedScreen(navController, mainTopicId, mainTopicTitle)
        }
    }
}