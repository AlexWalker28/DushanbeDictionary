package com.example.lol.dictionary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;


public class WordListAdapter extends BaseAdapter {

    private ArrayList<Word> wordsListData;
    private LayoutInflater layoutInflater;

    public WordListAdapter(Context aContext, ArrayList<Word> listData) {
        this.wordsListData = listData;
        layoutInflater = LayoutInflater.from(aContext);
    }

    @Override
    public int getCount() {
        return wordsListData.size();
    }

    @Override
    public Object getItem(int position) {
        return wordsListData.get(position);
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

        holder.translationWordOneTextView.setText(wordsListData.get(position).getLanguageOne());
        holder.translationTwoTextView.setText(wordsListData.get(position).getLanguageTwo());
        holder.translationThreeTextView.setText(wordsListData.get(position).getLanguageThree());
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
}