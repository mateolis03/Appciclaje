// Generated by view binder compiler. Do not edit!
package unipiloto.edu.co.appiclaje.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
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

public final class ActivityIngresarAplicacionBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final EditText emailAddress;

  @NonNull
  public final ImageView imageView;

  @NonNull
  public final Button ingresarButton;

  @NonNull
  public final Button olvidarButton;

  @NonNull
  public final EditText password;

  @NonNull
  public final Switch recordarSesion;

  @NonNull
  public final Button registrarButton;

  @NonNull
  public final TextView textView;

  private ActivityIngresarAplicacionBinding(@NonNull ConstraintLayout rootView,
      @NonNull EditText emailAddress, @NonNull ImageView imageView, @NonNull Button ingresarButton,
      @NonNull Button olvidarButton, @NonNull EditText password, @NonNull Switch recordarSesion,
      @NonNull Button registrarButton, @NonNull TextView textView) {
    this.rootView = rootView;
    this.emailAddress = emailAddress;
    this.imageView = imageView;
    this.ingresarButton = ingresarButton;
    this.olvidarButton = olvidarButton;
    this.password = password;
    this.recordarSesion = recordarSesion;
    this.registrarButton = registrarButton;
    this.textView = textView;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityIngresarAplicacionBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityIngresarAplicacionBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_ingresar_aplicacion, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityIngresarAplicacionBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.email_address;
      EditText emailAddress = ViewBindings.findChildViewById(rootView, id);
      if (emailAddress == null) {
        break missingId;
      }

      id = R.id.imageView;
      ImageView imageView = ViewBindings.findChildViewById(rootView, id);
      if (imageView == null) {
        break missingId;
      }

      id = R.id.ingresar_button;
      Button ingresarButton = ViewBindings.findChildViewById(rootView, id);
      if (ingresarButton == null) {
        break missingId;
      }

      id = R.id.olvidar_button;
      Button olvidarButton = ViewBindings.findChildViewById(rootView, id);
      if (olvidarButton == null) {
        break missingId;
      }

      id = R.id.password;
      EditText password = ViewBindings.findChildViewById(rootView, id);
      if (password == null) {
        break missingId;
      }

      id = R.id.recordar_sesion;
      Switch recordarSesion = ViewBindings.findChildViewById(rootView, id);
      if (recordarSesion == null) {
        break missingId;
      }

      id = R.id.registrar_button;
      Button registrarButton = ViewBindings.findChildViewById(rootView, id);
      if (registrarButton == null) {
        break missingId;
      }

      id = R.id.textView;
      TextView textView = ViewBindings.findChildViewById(rootView, id);
      if (textView == null) {
        break missingId;
      }

      return new ActivityIngresarAplicacionBinding((ConstraintLayout) rootView, emailAddress,
          imageView, ingresarButton, olvidarButton, password, recordarSesion, registrarButton,
          textView);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
