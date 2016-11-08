package com.wmz.demo_w1.activity;

import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.GridHolder;
import com.orhanobut.dialogplus.Holder;
import com.orhanobut.dialogplus.ListHolder;
import com.orhanobut.dialogplus.OnBackPressListener;
import com.orhanobut.dialogplus.OnCancelListener;
import com.orhanobut.dialogplus.OnClickListener;
import com.orhanobut.dialogplus.OnDismissListener;
import com.orhanobut.dialogplus.OnItemClickListener;
import com.orhanobut.dialogplus.ViewHolder;
import com.wmz.demo_w1.R;
import com.wmz.demo_w1.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class DialogActivity extends BaseActivity {


    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_dialog;
    }

    @BindView(R.id.radio_group)
    RadioGroup radioGroup;
    @BindView(R.id.header_check_box)
    CheckBox headerCheckBox;
    @BindView(R.id.footer_check_box)
    CheckBox footerCheckBox;
    @BindView(R.id.expanded_check_box)
    CheckBox expandedCheckBox;

    @OnClick(R.id.button_bottom)
    void bottom(){
        showDialog(
                radioGroup.getCheckedRadioButtonId(),
                Gravity.BOTTOM,
                headerCheckBox.isChecked(),
                footerCheckBox.isChecked(),
                expandedCheckBox.isChecked()
        );
    }

    @OnClick(R.id.button_center)
    void center(){
        showDialog(
                radioGroup.getCheckedRadioButtonId(),
                Gravity.CENTER,
                headerCheckBox.isChecked(),
                footerCheckBox.isChecked(),
                expandedCheckBox.isChecked()
        );
    }

    @OnClick(R.id.button_top)
    void top(){
        showDialog(
                radioGroup.getCheckedRadioButtonId(),
                Gravity.TOP,
                headerCheckBox.isChecked(),
                footerCheckBox.isChecked(),
                expandedCheckBox.isChecked()
        );
    }

    @Override
    protected void initView() {
        View contentView = getLayoutInflater().inflate(R.layout.content2, null);
        DialogPlus dialogPlus = DialogPlus.newDialog(this)
                .setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, new String[]{"asdfa"}))
                .setCancelable(true)
                .setOnDismissListener(new OnDismissListener() {
                    @Override
                    public void onDismiss(DialogPlus dialog) {
                    }
                })
                .setOnCancelListener(new OnCancelListener() {
                    @Override
                    public void onCancel(DialogPlus dialog) {

                    }
                })
                .setOnBackPressListener(new OnBackPressListener() {
                    @Override
                    public void onBackPressed(DialogPlus dialogPlus) {

                    }
                })
                .create();

        dialogPlus.show();
    }

    private void showDialog(int holderId, int gravity, boolean showHeader, boolean showFooter, boolean expanded) {
        boolean isGrid;
        Holder holder;
        switch (holderId) {
            case R.id.basic_holder_radio_button:
                holder = new ViewHolder(R.layout.content);
                isGrid = false;
                break;
            case R.id.list_holder_radio_button:
                holder = new ListHolder();
                isGrid = false;
                break;
            default:
                holder = new GridHolder(3);
                isGrid = true;
        }

        OnClickListener clickListener = new OnClickListener() {
            @Override
            public void onClick(DialogPlus dialog, View view) {
                        switch (view.getId()) {
                          case R.id.header_container:
                            Toast.makeText(DialogActivity.this, "Header clicked", Toast.LENGTH_LONG).show();
                            break;
                          case R.id.like_it_button:
                            Toast.makeText(DialogActivity.this, "We're glad that you like it", Toast.LENGTH_LONG).show();
                            break;
                          case R.id.love_it_button:
                            Toast.makeText(DialogActivity.this, "We're glad that you love it", Toast.LENGTH_LONG).show();
                            break;
                          case R.id.footer_confirm_button:
                            Toast.makeText(DialogActivity.this, "Confirm button clicked", Toast.LENGTH_LONG).show();
                            break;
                          case R.id.footer_close_button:
                            Toast.makeText(DialogActivity.this, "Close button clicked", Toast.LENGTH_LONG).show();
                            break;
                        }
                        dialog.dismiss();
            }
        };

        OnItemClickListener itemClickListener = new OnItemClickListener() {
            @Override
            public void onItemClick(DialogPlus dialog, Object item, View view, int position) {
                TextView textView = (TextView) view.findViewById(R.id.text_view);
                String clickedAppName = textView.getText().toString();
                        dialog.dismiss();
                        Toast.makeText(DialogActivity.this, clickedAppName + " clicked", Toast.LENGTH_LONG).show();
            }
        };

        OnDismissListener dismissListener = new OnDismissListener() {
            @Override
            public void onDismiss(DialogPlus dialog) {
                        Toast.makeText(DialogActivity.this, "dismiss listener invoked!", Toast.LENGTH_SHORT).show();
            }
        };

        OnCancelListener cancelListener = new OnCancelListener() {
            @Override
            public void onCancel(DialogPlus dialog) {
                        Toast.makeText(DialogActivity.this, "cancel listener invoked!", Toast.LENGTH_SHORT).show();
            }
        };

        SimpleAdapter adapter = new SimpleAdapter(DialogActivity.this, isGrid);
        if (showHeader && showFooter) {
            showCompleteDialog(holder, gravity, adapter, clickListener, itemClickListener, dismissListener, cancelListener,
                    expanded);
            return;
        }

        if (showHeader && !showFooter) {
            showNoFooterDialog(holder, gravity, adapter, clickListener, itemClickListener, dismissListener, cancelListener,
                    expanded);
            return;
        }

        if (!showHeader && showFooter) {
            showNoHeaderDialog(holder, gravity, adapter, clickListener, itemClickListener, dismissListener, cancelListener,
                    expanded);
            return;
        }

        showOnlyContentDialog(holder, gravity, adapter, itemClickListener, dismissListener, cancelListener, expanded);
    }

    private void showCompleteDialog(Holder holder, int gravity, BaseAdapter adapter,
                                    OnClickListener clickListener, OnItemClickListener itemClickListener,
                                    OnDismissListener dismissListener, OnCancelListener cancelListener,
                                    boolean expanded) {
        final DialogPlus dialog = DialogPlus.newDialog(this)
                .setContentHolder(holder)
                .setHeader(R.layout.header)
                .setFooter(R.layout.footer)
                .setCancelable(true)
                .setGravity(gravity)
                .setAdapter(adapter)
                .setOnClickListener(clickListener)
                .setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(DialogPlus dialog, Object item, View view, int position) {
                        Log.d("DialogPlus", "onItemClick() called with: " + "item = [" +
                                item + "], position = [" + position + "]");
                    }
                })
                .setOnDismissListener(dismissListener)
                .setExpanded(expanded)
//        .setContentWidth(800)
                .setContentHeight(ViewGroup.LayoutParams.WRAP_CONTENT)
                .setOnCancelListener(cancelListener)
                .setOverlayBackgroundResource(android.R.color.transparent)
//        .setContentBackgroundResource(R.drawable.corner_background)
                //                .setOutMostMargin(0, 100, 0, 0)
                .create();
        dialog.show();
    }

    private void showNoFooterDialog(Holder holder, int gravity, BaseAdapter adapter,
                                    OnClickListener clickListener, OnItemClickListener itemClickListener,
                                    OnDismissListener dismissListener, OnCancelListener cancelListener,
                                    boolean expanded) {
        final DialogPlus dialog = DialogPlus.newDialog(this)
                .setContentHolder(holder)
                .setHeader(R.layout.header)
                .setCancelable(true)
                .setGravity(gravity)
                .setAdapter(adapter)
                .setOnClickListener(clickListener)
                .setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(DialogPlus dialog, Object item, View view, int position) {
                        Log.d("DialogPlus", "onItemClick() called with: " + "item = [" +
                                item + "], position = [" + position + "]");
                    }
                })
                .setOnDismissListener(dismissListener)
                .setOnCancelListener(cancelListener)
                .setExpanded(expanded)
                .create();
        dialog.show();
    }

    private void showNoHeaderDialog(Holder holder, int gravity, BaseAdapter adapter,
                                    OnClickListener clickListener, OnItemClickListener itemClickListener,
                                    OnDismissListener dismissListener, OnCancelListener cancelListener,
                                    boolean expanded) {
        final DialogPlus dialog = DialogPlus.newDialog(this)
                .setContentHolder(holder)
                .setFooter(R.layout.footer)
                .setCancelable(true)
                .setGravity(gravity)
                .setAdapter(adapter)
                .setOnClickListener(clickListener)
                .setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(DialogPlus dialog, Object item, View view, int position) {
                        Log.d("DialogPlus", "onItemClick() called with: " + "item = [" +
                                item + "], position = [" + position + "]");
                    }
                })
                .setOnDismissListener(dismissListener)
                .setOnCancelListener(cancelListener)
                .setExpanded(expanded)
                .create();
        dialog.show();
    }

    private void showOnlyContentDialog(Holder holder, int gravity, BaseAdapter adapter,
                                       OnItemClickListener itemClickListener, OnDismissListener dismissListener,
                                       OnCancelListener cancelListener, boolean expanded) {
        final DialogPlus dialog = DialogPlus.newDialog(this)
                .setContentHolder(holder)
                .setGravity(gravity)
                .setAdapter(adapter)
                .setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(DialogPlus dialog, Object item, View view, int position) {
                        Log.d("DialogPlus", "onItemClick() called with: " + "item = [" +
                                item + "], position = [" + position + "]");
                    }
                })
                .setOnDismissListener(dismissListener)
                .setOnCancelListener(cancelListener)
                .setExpanded(expanded)
                .setCancelable(true)
                .create();
        dialog.show();
    }
}
