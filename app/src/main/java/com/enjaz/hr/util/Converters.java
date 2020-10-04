package com.enjaz.hr.util;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class Converters {

	/*@TypeConverter
	public static List<Movie> StringToMovies(String string) {
		return new Gson().fromJson(string, new TypeToken<List<Movie>>() {
		}.getType());
	}
	@TypeConverter
	public static List<Category> StringToCategories(String string) {
		return new Gson().fromJson(string, new TypeToken<List<Category>>() {
		}.getType());
	}

	@TypeConverter
	public static String CategoriesToString(List<Category> information) {
		Gson gson = new Gson();
		return gson.toJson(information);
	}
	@TypeConverter
	public static String MoviesToString(List<Movie> information) {
		Gson gson = new Gson();
		return gson.toJson(information);
	}
*/

    @TypeConverter
    public static List<String> StringToGenres(String string) {
        return new Gson().fromJson(string, new TypeToken<List<String>>() {
        }.getType());
    }

    @TypeConverter
    public static String GenresToString(List<String> information) {

        Gson gson = new Gson();
        return gson.toJson(information);
    }
}
