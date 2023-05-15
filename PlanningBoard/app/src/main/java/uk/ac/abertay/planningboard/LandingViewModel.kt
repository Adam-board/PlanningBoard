package uk.ac.abertay.planningboard



import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class LandingViewModel:ViewModel() {


    private val _todoToFinish = MutableLiveData<Int>()
    val todoToFinish : LiveData<Int> get() = _todoToFinish

    private val _notesCreated = MutableLiveData<Int>()
    val notesCreated : LiveData<Int> get() = _notesCreated


    //The current quote displayed on screen
    private val _quote = MutableLiveData<String>()
    val quote : LiveData<String> get() = _quote


    private lateinit var quoteList: MutableList<String>


    init{

        _quote.value = "Loading..."
        resetQuote()
        nextQuote()
    }


    private fun resetQuote() {
        quoteList = mutableListOf(
            "The greatest glory in living lies not in never falling, but in rising every time we fall. -Nelson Mandela",
            "The way to get started is to quit talking and begin doing. -Walt Disney",
            "Your time is limited, so don't waste it living someone else's life. Don't be trapped by dogma – which is living with the results of other people's thinking. -Steve Jobs",
            "If life were predictable it would cease to be life, and be without flavor. -Eleanor Roosevelt",
            "If you look at what you have in life, you'll always have more. If you look at what you don't have in life, you'll never have enough. -Oprah Winfrey",
            "If you set your goals ridiculously high and it's a failure, you will fail above everyone else's success. -James Cameron",
            "Don't judge each day by the harvest you reap but by the seeds that you plant. -Robert Louis Stevenson",
            "The future belongs to those who believe in the beauty of their dreams. -Eleanor Roosevelt",
            "It is during our darkest moments that we must focus to see the light. -Aristotle",
            "Do not go where the path may lead, go instead where there is no path and leave a trail. -Ralph Waldo Emerson"
        )
        quoteList.shuffle()
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("GameViewModel", "GameViewModel Destroyed!")
    }


     fun nextQuote() {
        //Select and remove a quote from the list
        if (quoteList.isEmpty()) {
            resetQuote()
        }
        _quote.value = quoteList.removeAt(0)
    }

}

