package ca.javateacher.catmessage6;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

@SuppressWarnings("WeakerAccess")
public class MessageViewModel extends ViewModel {
    private final MutableLiveData<String>
            mMessageData = new MutableLiveData<>();

    public MutableLiveData<String> getMessageData(){
        return mMessageData;
    }
}
