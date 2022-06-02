package basic.helper.finalproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class NamesQuestionsAdapter extends BaseAdapter {
    private Context ctx;
    private LayoutInflater lInflater;
    private ArrayList<NamesQuestions> objects = new ArrayList<NamesQuestions>();
    public NamesQuestionsAdapter(Context context) {
        ctx = context;
        lInflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return objects.size();
    }
    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }
    @Override
    public long getItemId (int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.item, parent, false);
        }
        NamesQuestions n = getNameQuestions(position);
        ((TextView) view.findViewById(R.id.item1)).setText(n.name);
        return view;
    }
    public NamesQuestions getNameQuestions(int position) {
        return ((NamesQuestions) getItem(position));
    }
    public void refresh (List<NamesQuestions> m) {
        if (m != null) {
            objects.clear();
            objects.addAll(m);
            notifyDataSetChanged();
        }
    }
}
