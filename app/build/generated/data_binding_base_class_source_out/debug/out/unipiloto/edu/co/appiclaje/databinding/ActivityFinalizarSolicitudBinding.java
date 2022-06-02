// Generated by view binder compiler. Do not edit!
package unipiloto.edu.co.appiclaje.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;
import unipiloto.edu.co.appiclaje.R;

public final class ActivityFinalizarSolicitudBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button ConsultarButton;

  @NonNull
  public final ImageView imageView;

  @NonNull
  public final ListView listPendiente;

  @NonNull
  public final TextView textView;

  private ActivityFinalizarSolicitudBinding(@NonNull ConstraintLayout rootView,
      @NonNull Button ConsultarButton, @NonNull ImageView imageView,
      @NonNull ListView listPendiente, @NonNull TextView textView) {
    this.rootView = rootView;
    this.ConsultarButton = ConsultarButton;
    this.imageView = imageView;
    this.listPendiente = listPendiente;
    this.textView = textView;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityFinalizarSolicitudBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityFinalizarSolicitudBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_finalizar_solicitud, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityFinalizarSolicitudBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.ConsultarButton;
      Button ConsultarButton = ViewBindings.findChildViewById(rootView, id);
      if (ConsultarButton == null) {
        break missingId;
      }

      id = R.id.imageView;
      ImageView imageView = ViewBindings.findChildViewById(rootView, id);
      if (imageView == null) {
        break missingId;
      }

      id = R.id.listPendiente;
      ListView listPendiente = ViewBindings.findChildViewById(rootView, id);
      if (listPendiente == null) {
        break missingId;
      }

      id = R.id.textView;
      TextView textView = ViewBindings.findChildViewById(rootView, id);
      if (textView == null) {
        break missingId;
      }

      return new ActivityFinalizarSolicitudBinding((ConstraintLayout) rootView, ConsultarButton,
          imageView, listPendiente, textView);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}