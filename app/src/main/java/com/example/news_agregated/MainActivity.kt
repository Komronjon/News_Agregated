package com.example.news_agregated

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import com.example.news_agregated.db.ArticleDatabase
import com.example.news_agregated.repository.NewsRepository
import com.google.android.material.bottomnavigation.BottomNavigationView



class MainActivity : AppCompatActivity() {

    lateinit var  homefragment:Fragment_home
    lateinit var historyfragment:Fragment_izbran
    lateinit var favoritfragment:Fragment_favorit
    lateinit var profilefragment:Fragment_profile
    lateinit var searchView:SearchViewFragment
    lateinit var viewModel:NewsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

         val newsRepository=NewsRepository(ArticleDatabase.invoke(this))
        val viewModelProviderFactory=NewsViewModelProviderFactory(newsRepository)
        viewModel=ViewModelProvider(this,viewModelProviderFactory).get(NewsViewModel::class.java)

        val bottomNavigationView:BottomNavigationView=findViewById(R.id.Bt_nav)

            homefragment= Fragment_home()
            supportFragmentManager.beginTransaction()
                .replace(R.id.ic_fragment,homefragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit()

        bottomNavigationView.setOnNavigationItemSelectedListener {item->
            when(item.itemId){

                R.id.home->{
                    homefragment= Fragment_home()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.ic_fragment,homefragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                }
                R.id.rvSearchNews->{
                    searchView= SearchViewFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.ic_fragment,searchView)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                }
                R.id.like->{
                    favoritfragment= Fragment_favorit()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.ic_fragment,favoritfragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                }
                R.id.profile->{
                    profilefragment= Fragment_profile()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.ic_fragment,profilefragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                }

            }

            true
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.select_menu,menu)
    // val search: MenuItem? = menu?.findItem(R.id.search)
//        val searchView:SearchView=search?.actionView as SearchView
//        searchView.queryHint="Search"
//         searchView.setOnQueryTextListener(object:SearchView.OnQueryTextListener{
//             override fun onQueryTextSubmit(query: String?): Boolean {
//                 TODO("Not yet implemented")
//             }
//
//             override fun onQueryTextChange(newText: String?): Boolean {
//                 TODO("Not yet implemented")
//             }
//
//         })

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
       when(item.itemId){
           R.id.search-> Toast.makeText(this,"search",Toast.LENGTH_SHORT).show()
          R.id.alert->Toast.makeText(this,"alert",Toast.LENGTH_SHORT).show()
      }

      return super.onOptionsItemSelected(item) }
}