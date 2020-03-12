package com.enjaz.university.util;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.enjaz.university.data.model.video.Category;
import com.enjaz.university.data.model.video.Ids;
import com.enjaz.university.data.model.video.Movie;

import java.util.List;

public class Converters {

	@TypeConverter
	public static Ids StringToIds(String string) {
		return new Gson().fromJson(string, new TypeToken<Ids>() {
		}.getType());
	}

	@TypeConverter
	public static String IdsToString(Ids information) {

		Ids ids = information;

		Gson gson = new Gson();
		String json = gson.toJson(ids);
		return json;
	}

	@TypeConverter
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


	@TypeConverter
	public static List<String> StringToGenres(String string) {
		return new Gson().fromJson(string, new TypeToken<List<String>>() {
		}.getType());
	}

	@TypeConverter
	public static String GenresToString(List<String> information) {

		Gson gson = new Gson();
		return gson.toJson(information);
	}}
