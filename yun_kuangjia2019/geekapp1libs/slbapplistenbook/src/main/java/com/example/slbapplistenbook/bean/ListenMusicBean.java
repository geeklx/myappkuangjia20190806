/*
 * Copyright (C) 2014 The CyanogenMod Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.slbapplistenbook.bean;

import android.os.Parcel;
import android.os.Parcelable;


/**
 * This is used by the music playback service to track the music tracks it is playing
 * It has extra meta data to determine where the track came from so that we can show the appropriate
 * song playing indicator
 */
public class ListenMusicBean implements Parcelable {


    public static final Creator<ListenMusicBean> CREATOR = new Creator<ListenMusicBean>() {
        @Override
        public ListenMusicBean createFromParcel(Parcel source) {
            return new ListenMusicBean(source);
        }

        @Override
        public ListenMusicBean[] newArray(int size) {
            return new ListenMusicBean[size];
        }
    };
    public int mId;
    public int mSourcePosition;
    public String mTitle;
    public String mAlbum;
    public String mArtist;

    public ListenMusicBean(int id, int sourcePosition) {
        mId = id;
        mSourcePosition = sourcePosition;
//        mTitle = title;
//        mArtist = artist;
//        mAlbum = album;
    }

    public ListenMusicBean(Parcel in) {
        mId = in.readInt();
        mSourcePosition = in.readInt();
//        mTitle = in.readString();
//        mArtist = in.readString();
//        mAlbum = in.readString();
    }

    public ListenMusicBean(int mId, int mSourcePosition, String mTitle, String mAlbum, String mArtist) {
        this.mId = mId;
        this.mSourcePosition = mSourcePosition;
        this.mTitle = mTitle;
        this.mAlbum = mAlbum;
        this.mArtist = mArtist;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public int getmSourcePosition() {
        return mSourcePosition;
    }

    public void setmSourcePosition(int mSourcePosition) {
        this.mSourcePosition = mSourcePosition;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmAlbum() {
        return mAlbum;
    }

    public void setmAlbum(String mAlbum) {
        this.mAlbum = mAlbum;
    }

    public String getmArtist() {
        return mArtist;
    }

    public void setmArtist(String mArtist) {
        this.mArtist = mArtist;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(mId);
        dest.writeInt(mSourcePosition);
//        dest.writeString(mTitle);
//        dest.writeString(mArtist);
//        dest.writeString(mArtist);

    }

}