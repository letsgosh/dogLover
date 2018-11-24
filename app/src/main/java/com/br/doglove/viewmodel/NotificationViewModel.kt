package com.br.doglove.viewmodel

import androidx.lifecycle.ViewModel;
import com.br.doglove.data.AppDatabase

class NotificationViewModel @javax.inject.Inject constructor(appDatabase: AppDatabase) : ViewModel() {

    val notification = appDatabase.notificationDao().getNotification()
}
