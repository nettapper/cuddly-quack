/*
 * Copyright 2015 Adriano Marini, Carson McLean, Conner Dunn, Daniel Haberstock, Garry Bullock
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ca.ualberta.cs.swapmyride;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.test.ActivityInstrumentationTestCase2;

import ca.ualberta.cs.swapmyride.Controller.VehicleController;
import ca.ualberta.cs.swapmyride.Model.Photo;
import ca.ualberta.cs.swapmyride.Model.Vehicle;
import ca.ualberta.cs.swapmyride.View.MainMenu;

/**
 * Created by Conner on 15-10-08.
 */
public class PhotoTest extends ActivityInstrumentationTestCase2 {

    public PhotoTest(){
        super(MainMenu.class);
    }
    /*
    // Use Case 23: Add Photo
    public void testAddPhotoToItem(){
        Photo picture = new Photo(getActivity());
        Photo tooLargePicture = new Photo(getActivity());
        Vehicle vehicle = new Vehicle();
        vehicle.setPhoto(picture);
        assertTrue(vehicle.getPhoto().equals(picture));
    }

    // Use Case 24: View Photo
    public void testViewPhoto() {
        Photo picture = new Photo(getActivity());
        Vehicle vehicle = new Vehicle();

    }

    // Use Case 25: Delete Photo
    public void testDeletePhoto(){
        Bitmap image = (BitmapFactory.decodeResource(getActivity().getResources(), R.drawable.ic_search));
        Photo photo1 = new Photo(image);
        Photo picture = new Photo (getActivity());
        Vehicle vehicle = new Vehicle();
        vehicle.setPhoto(photo1);
        assertTrue(vehicle.getPhoto().equals(photo1));
        new VehicleController().deletePhoto(vehicle, getActivity());
        assertFalse(vehicle.getPhoto().equals(photo1));
    }

    // Use Case 26: Restrict Image Size
    public void testTooLargePhoto(){
        Photo tooLargePicture = new Photo(getActivity());
        Vehicle vehicle = new Vehicle();
        Boolean thrown = true;
        try{
            vehicle.setPhoto(tooLargePicture);
        }
        catch(Exception e){
            thrown = true;
        }
        assertTrue(thrown);
    }

    // Use Case 27: Download Photo
    public void testDownloadPhoto(){
        Photo picture = new Photo(getActivity());
        // I'll be honest, not really sure how to test this one!
        // turn network off here some how
        Vehicle vehicle = new Vehicle();  // need to be an vehicle stored on remote

    }
    */
    
}

