package com.openclassrooms.savemytrip.todolist;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.openclassrooms.savemytrip.models.Item;
import com.openclassrooms.savemytrip.models.User;
import com.openclassrooms.savemytrip.repository.ItemDataRepository;
import com.openclassrooms.savemytrip.repository.UserDataRepository;

import java.util.List;
import java.util.concurrent.Executor;

public class ItemViewModel extends ViewModel {

    // REPOSITORIES

    private final ItemDataRepository itemDataSource;

    private final UserDataRepository userDataSource;

    private final Executor executor;

    // DATA

    @Nullable
    private LiveData<User> currentUser;

    public ItemViewModel(ItemDataRepository itemDataSource, UserDataRepository userDataSource, Executor executor) {

        this.itemDataSource = itemDataSource;

        this.userDataSource = userDataSource;

        this.executor = executor;

    }

    public void init(long userId) {

        if (this.currentUser != null) {

            return;

        }

        currentUser = userDataSource.getUser(userId);

    }

    // -------------

    // FOR USER

    // -------------

    public LiveData<User> getUser() { return this.currentUser;  }

    // -------------

    // FOR ITEM

    // -------------

    public LiveData<List<Item>> getItems(long userId) {

        return itemDataSource.getItems(userId);

    }

    public void createItem(String text, int category, long userId) {

        executor.execute(() -> {

            itemDataSource.createItem(new Item(text, category, userId));

        });

    }

    public void deleteItem(long itemId) {

        executor.execute(() -> itemDataSource.deleteItem(itemId));

    }

    public void updateItem(Item item) {

        executor.execute(() -> itemDataSource.updateItem(item));
    }

}
