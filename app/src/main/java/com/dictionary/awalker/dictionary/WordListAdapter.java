package com.dictionary.awalker.dictionary;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;


public class WordListAdapter extends BaseAdapter implements Filterable {



    private ArrayList<Word> wordsListData;
    private ArrayList<Word> filteredListData;
    private LayoutInflater layoutInflater;

    public WordListAdapter(Context aContext, ArrayList<Word> listData) {
        this.wordsListData = listData;
        this.filteredListData = listData;
        layoutInflater = LayoutInflater.from(aContext);
    }

    @Override
    public int getCount() {
        return filteredListData.size();
    }

    @Override
    public Object getItem(int position) {
        return filteredListData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.one_word_in_list, null);
            holder = new ViewHolder();
            holder.translationWordOneTextView = (TextView) convertView.findViewById(R.id.translationOneTextView);
            holder.translationTwoTextView = (TextView) convertView.findViewById(R.id.translationTwoTextView);
            holder.translationThreeTextView = (TextView) convertView.findViewById(R.id.translationThreeTextView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.translationWordOneTextView.setText(filteredListData.get(position).getLanguageOne());
        holder.translationTwoTextView.setText(filteredListData.get(position).getLanguageTwo());
        holder.translationThreeTextView.setText(filteredListData.get(position).getLanguageThree());
        return convertView;
    }

    public void add(Word word) {
        wordsListData.add(word);
        notifyDataSetChanged();
    }


    static class ViewHolder {
        TextView translationWordOneTextView;
        TextView translationTwoTextView;
        TextView translationThreeTextView;
    }


    @Override
    public Filter getFilter() {

        final Filter filter = new Filter() {

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                filteredListData = (ArrayList<Word>) results.values;
                notifyDataSetChanged();
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                FilterResults results = new FilterResults();
                ArrayList<Word> filteredArray = new ArrayList<>();


                // perform your search here using the searchConstraint String.

                constraint = constraint.toString().toLowerCase();
                for (int i = 0; i < wordsListData.size(); i++) {
                    Word word = wordsListData.get(i);
                    if (word.getLanguageOne().toLowerCase().startsWith(constraint.toString())
                            || word.getLanguageTwo().toLowerCase().startsWith(constraint.toString())
                            || word.getLanguageThree().toLowerCase().startsWith(constraint.toString()))  {
                        filteredArray.add(word);
                    }
                }

                results.count = filteredArray.size();
                results.values = filteredArray;
                Log.e("VALUES", results.values.toString());

                return results;
            }
        };

        return filter;
    }

}